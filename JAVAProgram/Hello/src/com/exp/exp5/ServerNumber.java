package com.exp.exp5;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ServerNumber {
    public static void main(String[] args) {
        ServerSocket server = null;
        ServerThread thread;
        Socket you = null;
        while (true) {
            try {
                server = new ServerSocket(4331);
            } catch (IOException e1) {
                System.out.println("正在监听");
            }
            try {
                you = server.accept();
                System.out.println("客户的地址：" + you.getInetAddress());
            } catch (IOException e) {
                System.out.println("正在等待客户");
            }
            if (you != null) {
                new ServerThread(you).start();
            } else {
                continue;
            }
        }
    }
}

class ServerThread extends Thread {
    Socket socket;
    DataInputStream in = null;
    DataOutputStream out = null;
    ServerThread (Socket t) {
        socket = t;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                String str = in.readUTF();
                System.out.println(str);
                boolean boo = (str.startsWith("Y") || str.startsWith("y"));
                System.out.println(boo);
                if (boo) {
                    out.writeUTF("给你一个1至100之间的随机数，请猜它是多少！");
                    Random random = new Random();
                    int realNumber = random.nextInt(100) + 1;
                    handleClientGuess(realNumber);
                    out.writeUTF("询问：想继续玩输入Y，否则输入N：");
                } else {
                    return;
                }
            }
        } catch (Exception e) {

        }
    }
    public void handleClientGuess(int realNumber) {
        while (true) {
            try {
                int clientGuess = in.readInt();
                System.out.println(clientGuess);
                if (clientGuess > realNumber) {
                    out.writeUTF("猜大了");
                } else if (clientGuess < realNumber) {
                    out.writeUTF("猜小了");
                } else if (clientGuess == realNumber){
                    out.writeUTF("猜对了");
                    break;
                }
            } catch (IOException e){
                System.out.println("客户离开");
                return;
            }
        }
    }
}
