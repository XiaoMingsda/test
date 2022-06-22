package com.exp.test;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ClientUI extends JDialog {
    private Socket clientSocket = null;
    private BufferedReader in = null; //网络输入流
    private PrintWriter out = null;   //网络输出流
    //窗口组件
    private JPanel contentPane;
    private JPanel topPanel;
    private JPanel midPanel;
    private JTextField txtRemotePort;
    private JButton btnConnect;
    private JButton btnSpeak;
    private JTextField txtInput;
    private JTextArea txtArea;
    private JTextField txtRemoteName;

    private Thread testThread = null;
    public ClientUI() {
        setContentPane(contentPane);
        setModal(true);

        // 连接服务器 按钮
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConnectActionPerformed();
            }
        });
        //发送消息 按钮
        btnSpeak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSpeakActionPerformed();
            }
        });
        //输入框按下 回车键， 也能发送消息
        txtInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInputActionPerformed();
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
        //关闭客户机之前的资源释放工作
        try {
            testThread.sleep(1000);
            if (out != null) out.close();
            if (in != null) {
                System.out.println("进来了");
                in.close();
            }
            System.out.println("asdasd");
            //关闭并销毁套接字


            //关闭并销毁网络流
            if (clientSocket != null) clientSocket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "关闭错误", JOptionPane.ERROR_MESSAGE);
        }
        System.exit(0);
    }

    //连接服务器
    private void btnConnectActionPerformed() {
        try {
            //获取服务器参数 服务器主机名
            String remoteName = txtRemoteName.getText();
            int remotePort = Integer.parseInt(txtRemotePort.getText());

            //创建SocketAddress
            SocketAddress remoteAddr = new InetSocketAddress(InetAddress.getByName(remoteName), remotePort);
            //创建套接字clientSocket并连接到远程服务器
            clientSocket = new Socket();
            clientSocket.connect(remoteAddr);
            txtArea.append("连接服务器成功，会话开始...\n");
            //绑定网络输入流和网络输出流
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "连接错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        btnConnect.setEnabled(false);

        testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        //按照 Echo协议，客户机应立即接受服务器回送消息
                        System.out.println(12312);
                        String inStr;
                        while ((inStr = in.readLine()) != null) {
                            //收到的Echo消息加入下面的文本框
                            txtArea.append("Echo：" + inStr + "\n");

                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "客户机接收消息错误！", "错误提示", JOptionPane.ERROR_MESSAGE);
                    }

            }
        });
        testThread.start();
    }

    //向服务器发送消息，并接受服务器的Echo消息
    private void btnSpeakActionPerformed() {
        //根据服务器协议，在网络流上进行读写操作
        if (clientSocket == null) {
            JOptionPane.showMessageDialog(null, "请先检查服务器连接情况。\n" +
                    "确保客户机连接到服务器！", "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //成功连接上服务器
        //获取代发消息
        String outStr = txtInput.getText();
        if (outStr.length() == 0) {//内容为空
            JOptionPane.showMessageDialog(null, "请输入发送消息！", "消息输入提示", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //发送
        out.println(outStr);
        txtInput.setText("");//清空输入框
        /*//读取服务器Echo消息
        try {
            //按照 Echo协议，客户机应立即接受服务器回送消息
            String inStr;
            inStr = in.readLine();
            //收到的Echo消息加入下面的文本框
            txtArea.append("Echo：" + inStr + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "客户机接收消息错误！", "错误提示", JOptionPane.ERROR_MESSAGE);
        }*/
    }

    //按下回车键，也能发送消息
    private void txtInputActionPerformed() {
        btnSpeakActionPerformed();
    }

    public static void main(String[] args) {
        ClientUI dialog = new ClientUI();
        dialog.setSize(800, 800);
        //dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
