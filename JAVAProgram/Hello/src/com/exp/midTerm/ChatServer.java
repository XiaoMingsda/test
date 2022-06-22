package com.exp.midTerm;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer extends JDialog {

    public static ExecutorService fixedPool; //线程池
    private ServerSocket listenSocket = null; //侦听套接字
    private Socket toClientSocket = null; //与客户机对话的套接字
    public static int clientCounts = 0; //客户数量编号
   // public static Set<Thread> threadsContainer = new HashSet<>();//
   public static Map<Thread, Integer> threadsContainer = new HashMap<>();
   public static int nThreads = 2;
   public static LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    //窗口组件
    private JPanel contentPane;
    private JTextField txtHostName;
    private JTextField txtHostPort;
    private JButton btnStart;
    private JTextArea txtArea;

    public ChatServer() {
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
        try {
            //关闭服务器之前
            if (listenSocket != null) listenSocket = null;
            if (toClientSocket != null) toClientSocket = null;
            if (fixedPool == null) return;
            fixedPool.shutdown();//线程池开始关闭
            if (!fixedPool.awaitTermination(60, TimeUnit.SECONDS)) {
                fixedPool.shutdown();
                if (!fixedPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    fixedPool.shutdownNow();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dispose();
    }

    //启动服务器
    private void btnStartActionPerformed() {
        ChatServer chatServer = this;
        try {
            //禁用按钮，避免重复启动
            btnStart.setEnabled(false);
            String hostName = txtHostName.getText();
            int  hostPort = Integer.parseInt(txtHostPort.getText());

            //构件服务器的SocketAddress格式地址
            SocketAddress serverAddr = new InetSocketAddress(InetAddress.getByName(hostName), hostPort);
            listenSocket = new ServerSocket();
            listenSocket.bind(serverAddr);
            //fixedPool = Executors.newFixedThreadPool(ChatServer.nThreads);
            fixedPool = new ThreadPoolExecutor(ChatServer.nThreads, ChatServer.nThreads, 0L, TimeUnit.MILLISECONDS, ChatServer.queue);
            txtArea.append("服务器开始等待客户机连接...\n");
        } catch (IOException ex) {}

        //创建一个匿名线程， 并创建响应客户机的会话线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {//处理客户机连接
                        toClientSocket = listenSocket.accept();//侦听并接受客户机连接
                        //获取用户名称
                        String userName;
                        userName = new BufferedReader(new InputStreamReader(toClientSocket.getInputStream(), "UTF-8")).readLine();
                        System.out.println("userName：" + userName);
                        //客户数量加1
                        ChatServer.clientCounts++;
                        txtArea.append(toClientSocket.getRemoteSocketAddress() + " 客户机编号：" + clientCounts + " 客户名称：" + userName + "  会话开始...\n");
                        new PrintWriter(new OutputStreamWriter(toClientSocket.getOutputStream(), "UTF-8"), true).println(ChatServer.clientCounts);
                        //创建客户线程clientThread
                        ClientThread clientThread = new ClientThread(toClientSocket, clientCounts, chatServer, userName);
                        //threadsContainer.add(clientThread);
                        threadsContainer.put(clientThread, clientCounts);
                        fixedPool.execute(clientThread);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        }).start();
    }

    //获取面板
    public JTextArea getTxtArea() {
        return txtArea;
    }

    public static void main(String[] args) {
        ChatServer dialog = new ChatServer();
        dialog.setSize(900, 800);
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
    private ChatServer chatServer = null;
    private String userName; //用户名称
    public ClientThread(Socket toClientSocket, int clientCounts, ChatServer chatServer, String userName) {
        this.toClientSocket = toClientSocket;
        this.clientCounts = clientCounts;
        this.chatServer = chatServer;
        this.userName = userName;
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
            out.println("成功进入聊天室");
            while ((recvStr = in.readLine()) != null) {
                System.out.println("收到信息：" + recvStr);
                //获取日期
                Date date = new Date();
                //DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = format.format(date);
                String msg = toClientSocket.getRemoteSocketAddress() + "  客户机编号： "
                        + clientCounts + "  客户名称： " + userName + "  ： " + recvStr + "   " + time + "\n";
                chatServer.getTxtArea().append(msg);

                //按照Echo协议原封不动回送消息
                //写个每一个客户机

                //Iterator<Thread> iterator = ChatServer.threadsContainer.iterator();
                Iterator<Thread> iterator = ChatServer.threadsContainer.keySet().iterator();
                while (iterator.hasNext()) {
                    //ClientThread clientThread = (ClientThread)iterator.next();
                    /*System.out.println("当前正在运行的线程：" + Thread.currentThread().getName());
                    System.out.println(clientThread);
                    System.out.println(clientThread.isAlive());
                    System.out.println(Thread.currentThread().isAlive());
                    if (clientThread.isAlive()) {
                        new PrintWriter(new OutputStreamWriter(clientThread.getToClientSocket().getOutputStream(), "UTF-8"), true).println(
                                clientThread.getToClientSocket().getLocalSocketAddress() + userName + " 说：" + recvStr + " : " + time
                        );
                    } else {
                        System.out.println("线程死亡");
                    }*/
                    ClientThread clientThread = (ClientThread)iterator.next();
                    System.out.println(clientThread);
                    System.out.println("不是1");

                    /*if (ChatServer.threadsContainer.get(clientThread)) {
                        new PrintWriter(new OutputStreamWriter(clientThread.getToClientSocket().getOutputStream(), "UTF-8"), true).println(
                                clientThread.getToClientSocket().getLocalSocketAddress() + userName + " 说：" + recvStr + " : " + time
                        );
                    } else {
                        System.out.println("不是2");
                        iterator.remove();
                    }*/

                    if (ChatServer.threadsContainer.get(clientThread) <= ChatServer.nThreads) {
                        new PrintWriter(new OutputStreamWriter(clientThread.getToClientSocket().getOutputStream(), "UTF-8"), true).println(
                                  userName + "  ： " + recvStr + "   " + time
                        );
                    }
                }
            }
            System.out.println("服务器端测试 客户机关闭了");
            if (ChatServer.clientCounts > ChatServer.nThreads) {
                //ClientThread queueFront = (ClientThread)ChatServer.queue.poll();
                ClientThread queueFront = (ClientThread)ChatServer.queue.element();
                System.out.println("阻塞队列：" + queueFront);
                System.out.println("编号：" + ChatServer.threadsContainer.get(this));
                System.out.println("编号：" + ChatServer.threadsContainer.get(queueFront));
                ChatServer.threadsContainer.put(queueFront, ChatServer.threadsContainer.get(this));
                System.out.println("编号：" + ChatServer.threadsContainer.get(queueFront));
            }
            ChatServer.clientCounts--;
            //远程客户机断开连接，线程释放资源
            if (in != null) in.close();
            if (out != null) out.close();
            if (toClientSocket != null) toClientSocket.close();
            ChatServer.threadsContainer.remove(this);
            System.out.println("断开连接");
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

    public ChatServer getChatServer() {
        return chatServer;
    }

    public void setChatServer(ChatServer chatServer) {
        this.chatServer = chatServer;
    }
}