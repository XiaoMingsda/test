package com.sockedclass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    /*
     * IDEA编写的项目如何在命令行窗口上运行？
     * https://blog.csdn.net/qq_40275740/article/details/104138149
     * */
    public static void main(String[] args) {
        String[] mes = {"chile", "meishui", "zaixue"};
        DataInputStream in = null;
        DataOutputStream out = null;
        Socket socket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(2010);
        } catch (Exception e) {

        }
        try {
            socket = serverSocket.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            for (int i = 0; i < mes.length; i++) {
                String anwser = in.readUTF();
                System.out.println("shoudaole:" + anwser);
                out.writeUTF(mes[i]);
                Thread.sleep(500);
            }
        } catch (Exception e) {

        }
    }
}
