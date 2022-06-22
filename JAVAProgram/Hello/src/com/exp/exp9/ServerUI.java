package com.exp.exp9;

import javax.swing.*;
import java.awt.event.*;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerUI extends JDialog {
    private JPanel contentPane;
    private JTextField txtHostName;
    private JTextField txtHostPort;
    private JButton btnStart;
    public JTextArea txtArea;
    private JButton buttonOK;

    public ServerUI() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);

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
        //关闭客户机之前的资源释放工作
        System.exit(0);
    }

    //启动服务器
    private void btnStartActionPerformed() {
        try {
            //获取服务器工作地址端口
            String hostName = txtHostName.getText();
            int hostPort = Integer.parseInt(txtHostPort.getText());
            //创建UDP数据报套接字，在指定端口侦听
            DatagramSocket serverSocket = new DatagramSocket(hostPort);
            txtArea.append("服务器开始侦听...\n");
            //创建UDP消息接收线程
            Thread recvThread = new ReceiveMessage2(serverSocket, this);
            recvThread.start();//启动线程
        } catch (NumberFormatException | SocketException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
        }
        btnStart.setEnabled(false);
    }

    public static void main(String[] args) {
        ServerUI dialog = new ServerUI();
        //dialog.pack();
        dialog.setSize(800, 600);
        dialog.setVisible(true);
        System.exit(0);
    }
}
