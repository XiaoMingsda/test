package com.exp.exp6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class MainForm {
    private JPanel root;
    private JButton btnConnect;
    private JTextField txtRemoteName;
    private JTextField txtRemotePort;
    private JTextField txtInput;
    private JButton btnSpeak;
    private JPanel topPanel;
    private JTextArea txtArea;

    private Socket clientSocket = null;
    public BufferedReader in;
    private PrintWriter out;


    public MainForm() {
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 try {
                     String remoteName = txtRemoteName.getText();
                     int remotePort = Integer.parseInt(txtRemotePort.getText());
                     SocketAddress remoteAddr = new InetSocketAddress(InetAddress.getByName(remoteName), remotePort);
                     clientSocket = new Socket();
                     clientSocket.connect(remoteAddr);
                     txtArea.append("连接服务器成功，会话开始...\n");
                     out = new PrintWriter(new OutputStreamWriter(
                             clientSocket.getOutputStream(), "UTF-8"), true);
                     in = new BufferedReader(new InputStreamReader(
                                clientSocket.getInputStream(), "UTF-8"
                     ));
                 } catch (IOException ex) {
                     JOptionPane.showMessageDialog(null, ex.getMessage(),
                             "连接错误", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 btnConnect.setEnabled(false);
            }
        });

        btnSpeak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientSocket == null) {
                    JOptionPane.showMessageDialog(null, "请先检查服务器连接情况。\n" +
                            "确保客户机连接到服务器！", "错误提示", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String outStr = txtInput.getText();
                if (outStr.length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入发送消息！", "提示",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                out.println(outStr);
                txtInput.setText("");
                try {
                    String inStr;
                    inStr = in.readLine();
                    txtArea.append("Echo: " + inStr + "\n");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "客户机接收消息错误！",
                            "错误提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == e.VK_ENTER) {
                    //按下 enter 键， 相当于btnSpeak被点击
                    btnSpeak.doClick();
                }
            }
        });
    }

    public void closeResource() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException ex) {

        }
    }

    public JPanel getRoot() {
        return root;
    }
}
