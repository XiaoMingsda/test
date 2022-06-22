package com.exp.exp9;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ClientUI extends JDialog {
    private DatagramSocket clientSocket;//客户机套接字
    private Message msg;//消息对象
    private byte[] data = new byte[8096];//8KB数组

    private JPanel contentPane;
    public JTextArea txtArea;
    private JButton btnSend;
    private JTextArea txtInput;
    public JList userList;

    public ClientUI() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);
        this.setSize(800, 800);
        // 2.设置关闭窗口就是关闭程序
        this.pack();
        this.setVisible(true);
        System.out.println(123123213);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSendActionPerformed();
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
        System.out.println("无参构造器被调用了");
    }

    public ClientUI(DatagramSocket socket, Message msg) {
        //this();
//        setContentPane(contentPane);
//        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);
        this();
        clientSocket = socket;//初始化会话套接字
        System.out.println("123123");
        this.msg = msg;//登录消息
        //创建客户机消息接收和处理线程
        Thread recvThread = new ReceiveMessage(clientSocket, this);
        recvThread.start();//启动消息接收线程
    }

    //关闭窗口事件
    private void onCancel() {
        // add your code here if necessary
        System.out.println("关闭窗口");
        //关闭客户机之前的资源释放工作
        try {
            msg.setType("M_QUIT");//消息类型
            msg.setText(null);
            data = Translate.ObjectToByte(msg);//消息对象序列化
            //构建发送
            DatagramPacket packet = new DatagramPacket(data, data.length, msg.getToAddr(), msg.getToPort());
            clientSocket.send(packet);
        } catch (IOException ex) { }
        clientSocket.close();//关闭套接字
        System.exit(0);
    }

    //发送消息
    private void btnSendActionPerformed() {
        try {
            msg.setText(txtInput.getText());//获取输入的文本
            msg.setType("M_MSG");//普通会话消息
            data = Translate.ObjectToByte(msg);//消息对象序列化
            //构建发送报文
            DatagramPacket packet = new DatagramPacket(data, data.length, msg.getToAddr(), msg.getToPort());
            clientSocket.send(packet);//发送
            txtInput.setText("");//清空输入框
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) throws SocketException {
/*        ClientUI dialog = new ClientUI();
//        //dialog.pack();
        dialog.setSize(800, 800);
        dialog.setVisible(true);
        System.exit(0);*/
    }
}
