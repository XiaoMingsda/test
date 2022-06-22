package com.exp.exp6;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerUI {
    private JPanel root;
    private JTextField txtHostName;
    private JTextField txtHostPort;
    private JButton btnStart;
    public  JTextArea txtArea;

    private ServerSocket listenSocket = null;
    private Socket toClientSocket = null;
    public static int clientCounts = 0;

    public ServerUI() {
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnStart.setEnabled(false);
                    String hostName = txtHostName.getText();
                    int hostPort = Integer.parseInt(txtHostPort.getText());
                    SocketAddress serverAddr = new InetSocketAddress(InetAddress.getByName(hostName), hostPort);
                    listenSocket = new ServerSocket();
                    listenSocket.bind(serverAddr);
                    txtArea.append("服务器开始等待客户机连接...\n");
                } catch (IOException ex){}

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                toClientSocket = listenSocket.accept();
                                clientCounts++;
                                txtArea.append(toClientSocket.getRemoteSocketAddress() +
                                        " 客户机编号： " + clientCounts + " 会话开始...\n");
                                Thread clientThread = new ClientThread(toClientSocket, clientCounts);
                                clientThread.start();
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }).start();
            }
        });
    }

    class ClientThread extends Thread{
        private Socket toClientSocket = null;
        private BufferedReader in;
        private PrintWriter out;
        private int clientCounts = 0;
        public ClientThread(Socket toClientSocket, int clientCounts) {
            this.toClientSocket = toClientSocket;
            this.clientCounts = clientCounts;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(toClientSocket.getInputStream(), "UTF-8"));
                out = new PrintWriter(new OutputStreamWriter(toClientSocket.getOutputStream(), "UTF-8"), true);
                String recvStr;
                while ((recvStr = in.readLine()) != null) {
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    String time = format.format(date);
                    //ServerUI serverUI = new ServerUI();
                    txtArea.append(toClientSocket.getRemoteSocketAddress() + "客户机编号：" + clientCounts + " 消息：" + recvStr + " : " + time + "\n");
                    out.println(toClientSocket.getLocalSocketAddress() + "客户机编号：" + clientCounts + " Echo消息：" + recvStr + " : " + time);
                }
                ServerUI.clientCounts--;
                if (in != null) in.close();
                if (out != null) out.close();
                if (toClientSocket != null) toClientSocket.close();
            } catch (IOException ex) {}
        }
    }

   

    public void closeResource() {
            if (listenSocket != null) listenSocket = null;
            if (toClientSocket != null) toClientSocket = null;
    }

    public JPanel getRoot() {
        return root;
    }
}
