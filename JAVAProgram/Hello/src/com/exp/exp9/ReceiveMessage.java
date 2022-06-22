package com.exp.exp9;

import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveMessage extends Thread{
    private DatagramSocket clientSocket;//会话套接字
    private ClientUI parentUI;//父类
    private byte[] data = new byte[8096];//8KB数组
    private DefaultListModel listModel = new DefaultListModel();//列表Model
    //构造函数
    public ReceiveMessage(DatagramSocket socket, ClientUI parentUI) {
        clientSocket = socket;//会话套接字
        this.parentUI = parentUI;//父类
    }

    @Override
    public void run() {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(data, data.length);//构建接收报文
                clientSocket.receive(packet);//接收
                Message msg = (Message) Translate.ByteToObject(data);//还原消息对象
                String userId = msg.getUserId();//当前用户id
                //根据消息类型分类处理
                if (msg.getType().equalsIgnoreCase("M_LOGIN")) {
                    parentUI.txtArea.append(userId + " 昂首挺胸进入聊天室......\n");
                    //新上线用户加入列表
                    listModel.add(listModel.getSize(), userId);
                    parentUI.userList.setModel(listModel);
                } else if (msg.getType().equalsIgnoreCase("M_ACK")) {
                    //服务器确认消息
                    //登录成功，将自己加入用户列表
                    listModel.add(listModel.getSize(), userId);
                    parentUI.userList.setModel(listModel);
                } else if (msg.getType().equalsIgnoreCase("M_MSG")) {
                    //普通会话消息
                    //更新消息窗口
                    parentUI.txtArea.append(userId + " 说：" + msg.getText() + "\n");
                } else if (msg.getType().equalsIgnoreCase("M_QUIT")) {
                    //其他用户下线消息
                    //更新窗口
                    parentUI.txtArea.append(userId + " 大步流星离开聊天室...\n");
                    //下线用户从列表删除
                    listModel.remove(listModel.indexOf(userId));
                    parentUI.userList.setModel(listModel);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
