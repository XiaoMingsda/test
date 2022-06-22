package com.exp.exp8;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NIOServer extends JDialog {

    private ServerSocketChannel listenChannel = null;//侦听通道
    private Selector selector;//选择器

    //窗口组件
    private JPanel contentPane;
    private JTextField txtHostName;
    private JTextField txtHostPort;
    private JButton btnStart;
    private JTextArea txtArea;

    public NIOServer() {
        setContentPane(contentPane);
        setModal(true);

        // 连接服务器 按钮
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
        System.exit(0);
    }

    //启动服务器
    private void btnStartActionPerformed() {
        btnStart.setEnabled(false);
        try {
            //构建工作地址
            String hostName = txtHostName.getText();
            int hostPort = Integer.parseInt(txtHostPort.getText());
            SocketAddress severAddr = new InetSocketAddress(InetAddress.getByName(hostName), hostPort);
            selector = Selector.open();//创建选择器
            listenChannel = ServerSocketChannel.open();//创建侦听通道
            listenChannel.socket().bind(severAddr);//侦听通道绑定工作地址
            listenChannel.configureBlocking(false);//侦听通道工作于非阻塞模式
            //侦听通道注册到选择器，设置 OP_ACCEPT 标志位 接受连接
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
            txtArea.append("服务器开始侦听客户机连接......\n");
        } catch (IOException ex) { }

        //服务器轮询线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {//轮询各通道状态，处理连接和会话
                        int nKeys = selector.select();//查询令牌集合
                        if (nKeys == 0) continue;//没有就绪令牌，越过下面步骤，开始新一轮查询
                        Set<SelectionKey> readyKeys = selector.selectedKeys();//返回就绪令牌集合
                        Iterator<SelectionKey> iterator = readyKeys.iterator();//就绪令牌集合迭代器
                        while (iterator.hasNext()) {//遍历就绪令牌集合
                            SelectionKey key = iterator.next();//取出下一个令牌
                            if (key.isAcceptable()) {//如果是连接事件
                                doAccept(key);//建立连接，创建新会话通道
                            } else if (key.isReadable()) {//如果是读数据事件
                                doRead(key);//接收数据
                            }
                            iterator.remove();//从就绪集合中删除处理过的令牌
                        }
                    }
                } catch (IOException ex) { }
            }
        }).start();

    }

    //处理连接
    public void doAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel(); //侦听通道
        SocketChannel clientChannel = serverChannel.accept();//接受连接
        txtArea.append("服务器建立了与客户机的会话通道：" + clientChannel + "\n");
        clientChannel.configureBlocking(false);//非阻塞
        //注册通道，协议作为附件
        Protocol protocol = new Protocol();//会话协议
        clientChannel.register(selector, SelectionKey.OP_READ, protocol);
    }

    //读取数据
    private void doRead(SelectionKey key) throws IOException {
        ByteBuffer recvBuff = ByteBuffer.allocate(1024);//接收缓冲区
        ByteBuffer sendBuff = ByteBuffer.allocate(1024);//发送缓冲区
        Charset charset = Charset.forName("UTF-8");//字符集
        SocketChannel clientChannel = (SocketChannel) key.channel();//会话通道
        Protocol protocol = (Protocol) key.attachment();//取出协议
        recvBuff.clear();//接收缓冲区清空
        clientChannel.read(recvBuff);//从通道读取数据
        recvBuff.flip();//缓冲区指针回到数据起点
        String recvStr = charset.decode(recvBuff).toString();//解码成字符串
        String sendStr = protocol.protocolWorking(recvStr);//求解回送字符串
        sendBuff.clear();//发送缓冲区清空
        sendBuff = ByteBuffer.wrap(sendStr.getBytes(charset));//发送字符串放入缓冲区
        System.out.println(sendBuff);
        clientChannel.write(sendBuff);//通道发送
        txtArea.append("玩家IP和端口：" + clientChannel.getRemoteAddress() + " 选择：" +
                recvStr + " <-->服务器选择：" + sendStr.substring(0, sendStr.indexOf("#")) +
                " 结果：" + sendStr.substring(sendStr.indexOf("#") + 1) + "\n");
    }

    public static void main(String[] args) {
        NIOServer dialog = new NIOServer();
        //dialog.pack();
        dialog.setSize(800, 800);
        dialog.setVisible(true);
        System.exit(0);
    }
}
