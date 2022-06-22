package com.exp.test;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServerUI extends JDialog {
    private ServerSocket listenSocket = null; //侦听套接字
    private Socket toClientSocket = null; //与客户机对话的套接字
    public static int clientCounts = 0; //客户数量编号

    public static Map<Integer, Socket> map = new HashMap<>();
    public static Set<Thread> threadsContainer = new HashSet<>();

    //窗口组件
    private JPanel contentPane;
    private JTextField txtHostName;
    private JTextField txtHostPort;
    private JButton btnStart;
    private JTextArea txtArea;

    public static String msg;

    public ServerUI() {
        setContentPane(contentPane);
        setModal(true);

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
        //关闭服务器之前
        if (listenSocket != null) listenSocket = null;
        if (toClientSocket != null) toClientSocket = null;
        dispose();
    }

    //启动服务器
    private void btnStartActionPerformed() {
        ServerUI serverUI = this;
        try {
            //禁用按钮，避免重复启动
            btnStart.setEnabled(false);
            String hostName = txtHostName.getText();
            int  hostPort = Integer.parseInt(txtHostPort.getText());
            //构件服务器的SocketAddress格式地址
            SocketAddress serverAddr = new InetSocketAddress(InetAddress.getByName(hostName), hostPort);
            listenSocket = new ServerSocket();
            listenSocket.bind(serverAddr);
            txtArea.append("服务器开始等待客户机连接...\n");
        } catch (IOException ex) {}

        //创建一个匿名线程， 并创建响应客户机的会话线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {//处理客户机连接
                        toClientSocket = listenSocket.accept();//侦听并接受客户机连接
                        clientCounts++;
                        txtArea.append(toClientSocket.getRemoteSocketAddress() + " 客户机编号：" + clientCounts + " 会话开始...\n");
                        //创建客户线程clientThread，实现一客户一线程
                        ClientThread clientThread = new ClientThread(toClientSocket, clientCounts, serverUI);
                        //map.put(clientCounts, toClientSocket);
                        threadsContainer.add(clientThread);
                        clientThread.start();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        }).start();



    }

    public JTextArea getTxtArea() {
        return txtArea;
    }

    public static void main(String[] args) {
        ServerUI dialog = new ServerUI();
        dialog.setSize(800, 800);
        //dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}


//客户机会话线程
class ClientThread extends Thread {
    private Socket toClientSocket = null;
    private BufferedReader in;
    private PrintWriter out;
    private int clientCounts = 0;//在线客户机总数
    private ServerUI serverUI = null;
    public ClientThread(Socket toClientSocket, int clientCounts, ServerUI serverUI) {
        this.toClientSocket = toClientSocket;
        this.clientCounts = clientCounts;
        this.serverUI = serverUI;
    }

    @Override
    public void run() {
        try {
            //创建绑定到套接字 toClientSocket 上的网络输入流和输出流
            in = new BufferedReader(new InputStreamReader(toClientSocket.getInputStream(), "UTF-8"));
            out = new PrintWriter(new OutputStreamWriter(toClientSocket.getOutputStream(), "UTF-8"), true);
            //根据服务器协议，在网络流上进行读写操作
            String recvStr;
            //客户机不关闭，反复等待和接收客户机消息
            while ((recvStr = in.readLine()) != null) {
                Date date = new Date();
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String time = format.format(date);
                String msg = toClientSocket.getRemoteSocketAddress() + "客户机编号："
                        + clientCounts + " 消息：" + recvStr + " : " + time + "\n";
                //ServerUI.msg = "12312312";
                serverUI.getTxtArea().append(msg);

                //按照Echo协议原封不动回送消息
                //写个每一个客户机
                /*
                Iterator<Socket> iterator = ServerUI.map.values().iterator();
                while (iterator.hasNext()){
                    Socket user = iterator.next();
                    Set<Thread> threadsContainer = new HashSet<>();
                    new PrintWriter(new OutputStreamWriter(user.getOutputStream(), "UTF-8"), true).println(toClientSocket.getLocalSocketAddress() + " 客户机编号：" +
                            clientCounts + " Echo消息：" + recvStr + " : " + time);
                }
                 */
                Iterator<Thread> iterator = ServerUI.threadsContainer.iterator();
                while (iterator.hasNext()) {
                    ClientThread thread = (ClientThread)iterator.next();
                    if (thread.isAlive()) {
                        new PrintWriter(new OutputStreamWriter(thread.getToClientSocket().getOutputStream(), "UTF-8"), true).println(
                                thread.getToClientSocket().getLocalSocketAddress() + " 客户机编号：" +clientCounts + " Echo消息：" + recvStr + " : " + time
                        );
                    }
                }
                //out.println(toClientSocket.getLocalSocketAddress() + " 客户机编号：" +
                       // clientCounts + " Echo消息：" + recvStr + " : " + time);

            }
            System.out.println("服务器端测试 客户机关闭了");
            ServerUI.clientCounts--;
            //远程客户机断开连接，线程释放资源
            if (in != null) in.close();
            if (out != null) out.close();
            if (toClientSocket != null) toClientSocket.close();
        } catch (IOException ex) {}
    }

    public Socket getToClientSocket() {
        return toClientSocket;
    }

    public void setToClientSocket(Socket toClientSocket) {
        this.toClientSocket = toClientSocket;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public int getClientCounts() {
        return clientCounts;
    }

    public void setClientCounts(int clientCounts) {
        this.clientCounts = clientCounts;
    }

    public ServerUI getServerUI() {
        return serverUI;
    }

    public void setServerUI(ServerUI serverUI) {
        this.serverUI = serverUI;
    }
}