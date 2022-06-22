package com.exp.exp6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class EchoServer {
    public static void main(String[] args) {
        ServerSocket listenSocket = null;
        Socket clientSocket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            listenSocket = new ServerSocket();
            SocketAddress serverAddr = new InetSocketAddress("localhost", 5000);
            listenSocket.bind(serverAddr);
            System.out.println("1.服务器启动成功！开始在localhost的 5000 端口侦听连接...");
            clientSocket = listenSocket.accept();
            System.out.println("2.客户机连接成功！客户机地址和端口：" + clientSocket.getRemoteSocketAddress());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            String recvStr = in.readLine();
            System.out.println("3.1 服务器收到字符串：" + recvStr);
            out.write(recvStr);
            out.newLine();
            out.flush();
            System.out.println("3.2 服务器回送字符串成功：" + recvStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (listenSocket != null) listenSocket.close();
            if (clientSocket != null) clientSocket.close();
            System.out.println("4. 关闭套接字和流成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
