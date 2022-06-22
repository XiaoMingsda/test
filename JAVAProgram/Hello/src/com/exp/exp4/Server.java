package com.exp.exp4;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        ServerThread2 thread2;
        Socket you = null;
        while (true) {
            try {
                server = new ServerSocket(4431);
            }
            catch (IOException e1) {
                System.out.println("正在监听");
            }
            try {
                you = server.accept();
                System.out.println("客户的地址:" + you.getInetAddress());
            }
            catch (IOException e) {
                System.out.println("正在等待客户");
            }
            if (you != null) {
                new ServerThread2(you).start();
            }
            else {
                continue;
            }
        }
    }
}

class ServerThread2 extends Thread {
    Socket socket;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    JFrame window;
    JTextArea text;
    ServerThread2(Socket t) {
        socket = t;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException e) {
        }
        window = new JFrame();
        text = new JTextArea();
        for (int i = 1; i <= 20; i++) {
            text.append("你好，我是服务器上的文本区组件\n");
        }
        text.setBackground(Color.yellow);
        window.add(text);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        try {
            out.writeObject(window);
        }
        catch (IOException e) {
            System.out.println("客户离开");
        }
    }
}