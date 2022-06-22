package com.exp.exp7.KK;

import com.exp.test.ServerUI;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KKServer extends JDialog {

    private ExecutorService fixedPool;//线程池
    private ServerSocket listenSocket;//侦听套接字
    private Socket toClientSocket;//与客户机对话的套接字
    public static int clientCounts = 0;//客户机数量

    //private Map<Integer, Socket> map = new HashMap<>();

    private JPanel contentPane;
    private JTextField txtHostName;
    private JTextField txtHostPort;
    private JButton btnStart;
    private JTextArea txtArea;

    public KKServer() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);

        // 启动服务器 按钮
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartActionPerformed();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        // 窗口关闭事件
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // 点击 esc 键退出窗口
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    //关闭窗口事件
    private void onCancel() {
        // add your code here if necessary
        System.out.println("关闭窗口");
        try {
            if (toClientSocket != null) toClientSocket.close();
            if (listenSocket != null) listenSocket.close();
            if (fixedPool == null) return;
            fixedPool.shutdown();//线程池开始关闭
            if (!fixedPool.awaitTermination(60, TimeUnit.SECONDS)) {
                fixedPool.shutdown();
                if (!fixedPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    fixedPool.shutdownNow();
                }
            }
        } catch (IOException | InterruptedException ex) { }
        dispose();
    }

    //启动服务器
    private void btnStartActionPerformed() {
        KKServer kkServer = this;
        try {
            btnStart.setEnabled(false);
            txtHostName.setEnabled(false);
            txtHostPort.setEnabled(false);
            //获取启动参数
            String hostName = txtHostName.getText();
            int hostPort = Integer.parseInt(txtHostPort.getText());
            //构建套接字格式的地址
            SocketAddress serverAddr = new InetSocketAddress(InetAddress.getByName(hostName), hostPort);
            listenSocket = new ServerSocket();
            listenSocket.bind(serverAddr);
            int processors = Runtime.getRuntime().availableProcessors();//CPU数
            fixedPool = Executors.newFixedThreadPool(processors * 2);//创建固定大小的线程池
            long currentId = Thread.currentThread().getId();
            txtArea.append("服务器CPU数：" + processors + "，固定线程池大小：" + processors * 2 +
                    "，当前侦听线程ID：" + currentId + "，服务器正等待客户机连接...\n");
        } catch (IOException ex) {}

        //连接线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        toClientSocket = listenSocket.accept();
                        clientCounts++;
                        txtArea.append(toClientSocket.getRemoteSocketAddress() + "客户机编号：" +
                                clientCounts + " 连接到服务器，会话开始...\n");
                        //创建客户线程
                        Thread clientThread = new ClientThread(toClientSocket, clientCounts, kkServer);
                        //System.out.println("线程名字：" + clientThread);
                        //map.put(clientCounts, toClientSocket);
                        fixedPool.execute(clientThread);//用线程池调度客户线程运行
                    }
                } catch (IOException ex) {}
            }
        }).start();
    }

    public JTextArea getTxtArea() {
        return txtArea;
    }

    public static void main(String[] args) {
        KKServer dialog = new KKServer();
        //dialog.pack();
        dialog.setSize(800, 800);
        dialog.setVisible(true);
        System.exit(0);
    }
}

class ClientThread extends Thread {
    private final Socket toClientSocket;//与客户机对话的套接字
    private BufferedReader in;
    private PrintWriter out;
    private Protocol protocol;
    private int clientCounts;
    private KKServer kkServer = null;

    public ClientThread(Socket toClientSocket, int clientCounts, KKServer kkServer) {
        this.toClientSocket = toClientSocket;
        this.clientCounts = clientCounts;
        this.kkServer = kkServer;
    }

    @Override
    public void run() {
        try {
            //创建绑定到套接字 toClientSocket 上的网络输入流和输出流
            in = new BufferedReader(new InputStreamReader(toClientSocket.getInputStream(), "UTF-8"));
            out = new PrintWriter(new OutputStreamWriter(toClientSocket.getOutputStream(), "UTF-8"), true);
            long currentId = Thread.currentThread().getId();
            kkServer.getTxtArea().append("当前会话线程ID：" + currentId + "\n");
            //根据服务器协议，在网络流上进行读写操作
            protocol = new Protocol();
            String outdoorStr;
            String indoorStr;
            outdoorStr = protocol.protocolWorking(null);
            out.println(outdoorStr);
            kkServer.getTxtArea().append("outdoor" + clientCounts + ": " + outdoorStr + "\n");
            while ((indoorStr = in.readLine()) != null) {
                kkServer.getTxtArea().append("indoor" + clientCounts + ": " + indoorStr + "\n");
                //根据协议生成回答消息
                outdoorStr = protocol.protocolWorking(indoorStr);
                out.println(outdoorStr);
                kkServer.getTxtArea().append("outdoor" + clientCounts + ": " + outdoorStr + "\n");
                if (outdoorStr.endsWith("Goodbye!"))
                    break;
            }
            KKServer.clientCounts--;
            //因为客户机断开了连接，所以释放资源
            if (in != null) in.close();
            if (out != null) out.close();
            if (toClientSocket != null) toClientSocket.close();
        } catch (IOException ex) {}
    }
}
