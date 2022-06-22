/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu;

import cn.edu.ldu.pojo.Member;
import cn.edu.ldu.util.Message;
import cn.edu.ldu.util.Translate;
import cn.edu.ldu.util.User;
import cn.edu.ldu.web.MemberWeb;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JOptionPane;
/**
 *
 * @author Administrator
 */
public class ReceiveMessage extends Thread{
    private DatagramSocket serverSocket;//服务器套接字
    private DatagramPacket packet;//报文
    private List<User> userList = new ArrayList<User>();//用户列表
    private byte[] data = new byte[8096];//8KB数组
    private ServerUI parentUI;//消息窗口
    private MemberWeb memberWeb = new MemberWeb();
    private Map<String, DatagramPacket> map = new HashMap<>();//客户机id 指向客户机的socket
    public static Map<String, Socket> phoneMap = new HashMap<>();//客户机id 指向客户机的Socket
    private ServerSocket serSock = null;
    public ReceiveMessage(DatagramSocket socket, ServerUI parentUI) {
        serverSocket = socket;
        this.parentUI = parentUI;
    }
    {
        try {
            serSock = new ServerSocket(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                packet = new DatagramPacket(data, data.length);//构建接收报文
                serverSocket.receive(packet);//接收客户机数据
                System.out.println("123");
                //收到的数据转为消息对象
                Message msg = (Message) Translate.ByteToObject(packet.getData());
                String userId = msg.getUserId();//当前消息来自用户的id
                //M_REGISTER
                if (msg.getType().equalsIgnoreCase("M_REGISTER")) {
                    Message backMsg = new Message();
                    Member member = new Member();
                    member.setId(Integer.parseInt(userId));
                    member.setPassword(msg.getPassword());
                    if (!memberWeb.registerMember(member)) {
                        //注册不成功的情况
                        backMsg.setType("M_FAILURE");
                        byte[] buf = Translate.ObjectToByte(backMsg);
                        DatagramPacket backPacket = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());//向注册用户发送的报文
                        serverSocket.send(backPacket);//发送
                    } else {
                        //发送注册成功的消息
                        backMsg.setType("M_SUCCESS");
                        byte[] buf = Translate.ObjectToByte(backMsg);
                        DatagramPacket backPacket = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());//向登录用户发送的报文
                        serverSocket.send(backPacket);//发送
                    }
                } else if (msg.getType().equalsIgnoreCase("M_LOGIN")) {
                    Message backMsg = new Message();
                      Member member = new Member();
                      member.setId(Integer.parseInt(userId));
                      member.setPassword(msg.getPassword());
                      if (!memberWeb.MemberLogin(member)) {
                        //登录不成功的情况
                        backMsg.setType("M_FAILURE");
                        byte[] buf = Translate.ObjectToByte(backMsg);
                        DatagramPacket backPacket = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());//向登录用户发送的报文
                        serverSocket.send(backPacket);//发送
                    } else {
                        //发送登录成功的消息
                        backMsg.setType("M_SUCCESS");
                        //将当前在线用户存放到map里
                        map.put(userId, packet);
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
                    //从在线用户列表map里移除
                    map.remove(userId);
                }
            } catch (IOException | NumberFormatException ex) { 
                ex.printStackTrace();
            }
        }
    }
}
