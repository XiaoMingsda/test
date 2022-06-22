package com.exp.exp9;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class ReceiveMessage2 extends Thread{
    private DatagramSocket serverSocket;//服务器套接字
    private DatagramPacket packet;//报文
    private List<User> userList = new ArrayList<User>();//用户列表
    private byte[] data = new byte[8096];//8KB数组
    private ServerUI parentUI;//消息窗口
    public ReceiveMessage2(DatagramSocket socket, ServerUI parentUI) {
        serverSocket = socket;
        this.parentUI = parentUI;
    }

    @Override
    public void run() {
        while (true) {
            try {
                packet = new DatagramPacket(data, data.length);//构建接收报文
                serverSocket.receive(packet);//接收客户机数据
                //收到的数据转为消息对象
                Message msg = (Message) Translate.ByteToObject(packet.getData());
                String userId = msg.getUserId();//当前消息来自用户的id
                if (msg.getType().equalsIgnoreCase("M_LOGIN")) {
                    Message backMsg = new Message();
                    //假定只有2000/3000/8000三个账号可以登录
                    if (!userId.equals("2000") && !userId.equals("3000") && !userId.equals("8000")) {
                        //登录不成功的情况
                        backMsg.setType("M_FAILURE");
                        byte[] buf = Translate.ObjectToByte(backMsg);
                        DatagramPacket backPacket = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());//向登录用户发送的报文
                        serverSocket.send(backPacket);//发送
                    } else {
                        backMsg.setType("M_SUCCESS");
                        byte[] buf = Translate.ObjectToByte(backMsg);
                        DatagramPacket backPacket = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());//向登录用户发送的报文
                        serverSocket.send(backPacket);//发送
                        User user = new User();
                        user.setUserId(userId);//用户名
                        user.setPacket(packet);//保存收到的报文
                        userList.add(user);//将新用户加入用户列表
                        //更新服务器聊天室大厅
                        parentUI.txtArea.append(userId + " 登录!\n");
                        //向所有其他在线用户发送M_LOGIN消息，向新登录者发送整个用户列表
                        for (int i = 0; i < userList.size(); i++) {
                            //遍历整个用户列表
                            //向其他在线用户发送M_LOGIN消息
                            if (!userId.equalsIgnoreCase(userList.get(i).getUserId())) {
                                DatagramPacket oldPacket = userList.get(i).getPacket();
                                DatagramPacket newPacket = new DatagramPacket(data, data.length, oldPacket.getAddress(), oldPacket.getPort());//转发的报文
                                serverSocket.send(newPacket);//发送
                            }
                            //向当前用户回送M_ACK消息，将第i个用户id加入其用户列表
                            Message other = new Message();
                            other.setUserId(userList.get(i).getUserId());
                            other.setType("M_ACK");
                            byte[] buffer = Translate.ObjectToByte(other);
                            DatagramPacket newPacket = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
                            serverSocket.send(newPacket);
                        }
                    }
                } else if (msg.getType().equalsIgnoreCase("M_MSG")) {
                    parentUI.txtArea.append(userId + " 说: " + msg.getText() + "\n");//更新提示
                    for (int i = 0; i < userList.size(); i++) {
                        //遍历用户，转发消息
                        DatagramPacket oldPacket = userList.get(i).getPacket();
                        DatagramPacket newPacket = new DatagramPacket(data, data.length, oldPacket.getAddress(), oldPacket.getPort());
                        serverSocket.send(newPacket);//发送
                    }
                } else if (msg.getType().equalsIgnoreCase("M_QUIT")) {
                    parentUI.txtArea.append(userId + " 下线！\n");//更新显示
                    for (int i = 0; i < userList.size(); i++) {
                        //从服务器的在线列表删除用户
                        if (userList.get(i).getUserId().equals(userId)) {
                            userList.remove(i);
                            break;
                        }
                    }
                    for (int i = 0; i < userList.size(); i++) {
                        //向其他用户转发下线消息
                        DatagramPacket oldPacket = userList.get(i).getPacket();
                        DatagramPacket newPacket = new DatagramPacket(data, data.length, oldPacket.getAddress(), oldPacket.getPort());
                        serverSocket.send(newPacket);
                    }
                }
            } catch (IOException | NumberFormatException ex) { }
        }
    }
}
