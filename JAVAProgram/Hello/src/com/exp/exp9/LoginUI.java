package com.exp.exp9;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class LoginUI extends JDialog {
    private JPanel contentPane;
    private JTextField txtUserId;
    private JButton btnRegister;
    private JPasswordField txtPassword;
    private JButton btnGetPassword;
    private JCheckBox chkRemember;
    private JCheckBox chkLogin;
    private JButton btnLogin;
    private JTextField txtRemoteName;
    private JTextField txtRemotePort;
    private LoginUI loginUI;
    public LoginUI() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);

        // 登录 按钮
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLoginActionPerformed();
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
        loginUI = this;
        // add your code here if necessary
        System.out.println("关闭窗口");
        //关闭客户机之前的资源释放工作
        //this.dispose();
        new ClientUI();//创建客户机界面
        System.out.println("显示会话窗体1");
    }

    //登录
    private void btnLoginActionPerformed() {
        try {
            String id = txtUserId.getText();
            String password = String.valueOf(txtPassword.getPassword());
            if (id.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "账号或密码不能为空！", "错误提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //获取服务器地址和端口
            String remoteName = txtRemoteName.getText();
            InetAddress remoteAddr = InetAddress.getByName(remoteName);
            int remotePort = Integer.parseInt(txtRemotePort.getText());
            //创建UDP套接字
            DatagramSocket clientSocket = new DatagramSocket();
            clientSocket.setSoTimeout(3000);//设置超时时间
            //构建用户登录消息
            Message msg = new Message();
            msg.setUserId(id);//登录名
            msg.setPassword(password);//密码
            msg.setType("M_LOGIN");//登录消息类型
            msg.setToAddr(remoteAddr);//目标主机地址
            msg.setToPort(remotePort);//目标主机端口
            byte[] data = Translate.ObjectToByte(msg);//消息对象序列化与字节数组
            //定义登录报文
            DatagramPacket packet = new DatagramPacket(data, data.length, remoteAddr, remotePort);
            //发送登录报文
            clientSocket.send(packet);
            //接收服务器回送的报文
            DatagramPacket backPacket = new DatagramPacket(data, data.length);
            clientSocket.receive(backPacket);
            clientSocket.setSoTimeout(0);//取消超时时间
            Message backMsg = (Message) Translate.ByteToObject(data);
            //处理登录结果
            if (backMsg.getType().equalsIgnoreCase("M_SUCCESS")) {
                //登录成功
                //this.dispose();//关闭登录对话框
                //onCancel();
                /*ClientUI client = new ClientUI(clientSocket, msg);//创建客户机界面
                client.setSize(800, 800);
                client.setTitle(msg.getUserId());//设置标题
                client.setVisible(true);//显示会话窗体
                System.out.println("显示会话窗体");*/
                this.dispose();
                new ClientUI();
                System.out.println(2);
            } else {
                //登录失败
                JOptionPane.showMessageDialog(null, "用户ID或密码错误!\n", "登录失败", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "登录错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        LoginUI dialog = new LoginUI();
        //dialog.pack();
        dialog.setSize(800, 400);
        dialog.setVisible(true);
        System.exit(0);
    }
}
