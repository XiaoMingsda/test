package com.exp.exp7.KK;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KKClient extends JDialog {
    private Socket clientSocket = null;//客户机套接字
    private BufferedReader in;
    private PrintWriter out;

    private JPanel contentPane;
    private JTextField txtRemoteName;
    private JTextField txtRemotePort;
    private JButton btnConnect;
    private JTextArea txtArea;
    private JTextField txtInput;
    private JButton buttonOK;

    public KKClient() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);

        // 连接服务器 按钮
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConnectActionPerformed();
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
        //获取输入焦点时清空文本框
        txtInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                //super.focusGained(e);
                txtInput.setText("");
            }
        });
    }

    //关闭窗口事件
    private void onCancel() {
        // add your code here if necessary
        System.out.println("关闭窗口");
        dispose();
    }

    //连接服务器
    private void btnConnectActionPerformed() {
        try {
            //获取参数
            String remoteName = txtRemoteName.getText();
            int remotePort = Integer.parseInt(txtRemotePort.getText());
            SocketAddress remoteAddr = new InetSocketAddress(remoteName, remotePort);
            clientSocket = new Socket();
            clientSocket.connect(remoteAddr);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
            String fromOutdoor = in.readLine();
            txtArea.append("outdoor: " + fromOutdoor + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "连接错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtRemoteName.setEnabled(false);
        txtRemotePort.setEnabled(false);
        btnConnect.setEnabled(false);
    }

    //按下回车键，也能发送消息
    private void txtInputActionPerformed() {
        String fromOutdoor;
        String fromIndoor;
        if (clientSocket == null) {
            JOptionPane.showMessageDialog(null, "请先连接服务器！", "连接错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            fromIndoor = txtInput.getText();
            if (!fromIndoor.equals("")) {
                out.println(fromIndoor);
                txtArea.append("indoor: " + fromIndoor + "\n");
                txtInput.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "问话内容为空，请重新输入！", "输入错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            fromOutdoor = in.readLine();
            txtArea.append("out.door: " + fromOutdoor + "\n");
            if (fromOutdoor.endsWith("Goodbye!")) {
                txtRemoteName.setEnabled(true);
                txtRemotePort.setEnabled(true);
                btnConnect.setEnabled(true);
                try {
                    if (in != null) in.close();
                    if (out != null) out.close();
                    if (clientSocket != null) clientSocket.close();
                } catch (IOException ex) { }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "接收数据错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        KKClient dialog = new KKClient();
        //dialog.pack();
        dialog.setSize(800, 800);
        dialog.setVisible(true);
        System.exit(0);
    }
}
