package com.sockedclass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String[] ques = {"吃饭了吗？", "睡觉了吗？", "学习了吗？"};
        DataInputStream in =  null;
        DataOutputStream out = null;
        try {
            Socket socket = new Socket("127.0.0.1", 2010);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            for (int i = 0; i < ques.length; i++) {
                out.writeUTF(ques[i]);
                String res = in.readUTF();
                System.out.println("客户收到服务器的回答:" + res);
                Thread.sleep(500);
            }
        } catch (Exception e) {

        }
    }
}
