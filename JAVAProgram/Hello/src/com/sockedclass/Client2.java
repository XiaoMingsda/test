package com.sockedclass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        Thread readData;
        Read read = null;
        try {
            socket = new Socket();
            read = new Read();
            readData = new Thread(read);
            System.out.println("输入服务器的IP:");
            String IP = scanner.nextLine();
            System.out.println("输入端口号:");
            int port = scanner.nextInt();
            if (socket.isConnected()) {}
            else {
                InetAddress byName = InetAddress.getByName(IP);
                InetSocketAddress inetSocketAddress = new InetSocketAddress(byName, port);
                socket.connect(inetSocketAddress);
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                read.setIn(in);
                readData.start();
            }
        } catch (Exception e) {
            System.out.println("服务器已断开" + e);
        }
        System.out.print("输入圆的半径(放弃请输入N):");
        while (scanner.hasNext()) {
            double radius = 0;
            try {
                radius = scanner.nextDouble();
            } catch (Exception e) {
                System.exit(0);
            }
            try {
                out.writeDouble(radius);
            } catch (Exception e) {

            }
        }
     }
}
