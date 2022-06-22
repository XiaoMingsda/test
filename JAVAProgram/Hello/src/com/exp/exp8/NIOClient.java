package com.exp.exp8;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NIOClient extends JDialog {
    private ByteBuffer recvBuff = ByteBuffer.allocate(1024);//接收缓冲区
    private ByteBuffer sendBuff = ByteBuffer.allocate(1024);//发送缓冲区
    private SocketChannel clientChannel = null;//会话通道
    private Charset charset = Charset.forName("UTF-8");
    private  int playerWin = 0;//玩家胜
    private  int serverWin = 0;//服务器胜
    private  int playerDraw = 0;//玩家平
    private  int serverDraw = 0;//服务器平

    private JPanel contentPane;
    private JTextField txtRemoteName;
    private JTextField txtRemotePort;
    private JButton btnStone;
    private JButton btnScissors;
    private JButton btnPaper;
    private JButton btnConnect;
    private JLabel lblPlayerChoice;
    private JLabel lblPlayerScore;
    private JLabel lblServerChoice;
    private JLabel lblServerScore;
    private JButton buttonOK;

    public NIOClient() {
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
        //出石头 按钮
        btnStone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStoneActionPerformed();
            }
        });
        //出剪刀 按钮
        btnScissors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnScissorsActionPerformed();
            }
        });
        //出布 按钮
        btnPaper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPaperActionPerformed();
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

    //连接服务器
    private void btnConnectActionPerformed() {
        try {
            //构建远程服务器地址
            String remoteName = txtRemoteName.getText();
            int remotePort = Integer.parseInt(txtRemotePort.getText());
            SocketAddress remoteAddr = new InetSocketAddress(InetAddress.getByName(remoteName), remotePort);
            clientChannel = SocketChannel.open();//创建客户机会话通道
            clientChannel.connect(remoteAddr);//连接远程服务器
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "连接错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        btnConnect.setEnabled(false);
    }

    //出石头
    private void btnStoneActionPerformed() {
        if (clientChannel == null) {
            JOptionPane.showMessageDialog(null, "请先连接服务器", "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            sendBuff.clear();//清空发送缓冲区
            sendBuff = ByteBuffer.wrap("Stone".getBytes(charset));//Stone字符串包装到缓冲区
            clientChannel.write(sendBuff);//首先告诉服务器自己的选择
            recvBuff.clear();//清空接收缓冲区
            clientChannel.read(recvBuff);//接收来自服务器的回复
            recvBuff.flip();//指针回至收到数据的起点
            String serverSide = charset.decode(recvBuff).toString();//解码收到的字符串
            //求解服务器返回的比赛结果，根据结果更新胜局和平局数
            String result = serverSide.substring(serverSide.indexOf("#") + 1);
            System.out.println(result);
            if (result.equalsIgnoreCase(" TwoDraw")) {
                playerDraw++;
                serverDraw++;
            } else if (result.equalsIgnoreCase(" ClientWin")) {
                playerWin++;
            } else if (result.equalsIgnoreCase(" ServerWin")) {
                serverWin++;
            }
            String playerScore = playerWin + " 胜 " + playerDraw + " 平 ";
            String serverScore =serverWin + " 胜 " + serverDraw + " 平 ";
            lblPlayerScore.setText(playerScore);
            lblServerScore.setText(serverScore);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
        }
    }
    //出剪刀
    private void btnScissorsActionPerformed() {
        if (clientChannel == null) {
            JOptionPane.showMessageDialog(null, "请先连接服务器", "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            sendBuff.clear();//清空发送缓冲区
            sendBuff = ByteBuffer.wrap("Scissors".getBytes(charset));//Stone字符串包装到缓冲区
            clientChannel.write(sendBuff);//首先告诉服务器自己的选择
            recvBuff.clear();//清空接收缓冲区
            clientChannel.read(recvBuff);//接收来自服务器的回复
            recvBuff.flip();//指针回至收到数据的起点
            String serverSide = charset.decode(recvBuff).toString();//解码收到的字符串
            //求解服务器返回的比赛结果，根据结果更新胜局和平局数
            String result = serverSide.substring(serverSide.indexOf("#") + 1);
            if (result.equalsIgnoreCase(" TwoDraw")) {
                playerDraw++;
                serverDraw++;
            } else if (result.equalsIgnoreCase(" ClientWin")) {
                playerWin++;
            } else if (result.equalsIgnoreCase(" ServerWin")) {
                serverWin++;
            }
            String playerScore = playerWin + " 胜 " + playerDraw + " 平 ";
            String serverScore =serverWin + " 胜 " + serverDraw + " 平 ";
            lblPlayerScore.setText(playerScore);
            lblServerScore.setText(serverScore);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
        }
    }
    //出布
    private void btnPaperActionPerformed() {
        if (clientChannel == null) {
            JOptionPane.showMessageDialog(null, "请先连接服务器", "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            sendBuff.clear();//清空发送缓冲区
            sendBuff = ByteBuffer.wrap("Paper".getBytes(charset));//Stone字符串包装到缓冲区
            clientChannel.write(sendBuff);//首先告诉服务器自己的选择
            recvBuff.clear();//清空接收缓冲区
            clientChannel.read(recvBuff);//接收来自服务器的回复
            recvBuff.flip();//指针回至收到数据的起点
            String serverSide = charset.decode(recvBuff).toString();//解码收到的字符串
            //求解服务器返回的比赛结果，根据结果更新胜局和平局数
            String result = serverSide.substring(serverSide.indexOf("#") + 1);
            if (result.equalsIgnoreCase(" TwoDraw")) {
                playerDraw++;
                serverDraw++;
            } else if (result.equalsIgnoreCase(" ClientWin")) {
                playerWin++;
            } else if (result.equalsIgnoreCase(" ServerWin")) {
                serverWin++;
            }

            String playerScore = playerWin + " 胜 " + playerDraw + " 平 ";
            String serverScore =serverWin + " 胜 " + serverDraw + " 平 ";
            lblPlayerScore.setText(playerScore);
            lblServerScore.setText(serverScore);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        NIOClient dialog = new NIOClient();
        //dialog.pack();
        dialog.setSize(800, 300);
        dialog.setVisible(true);
        System.exit(0);
    }
}
