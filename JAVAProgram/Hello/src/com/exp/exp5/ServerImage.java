package com.exp.exp5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerImage {
    public static void main(String[] args) {
        DatagramPacket pack = null;
        DatagramSocket mailReceive = null;
        ServerThread2 thread;
        byte b[] = new byte[8192];
        InetAddress address = null;
        pack = new DatagramPacket(b, b.length);
        while (true) {
            try {
                mailReceive = new DatagramSocket(1234);
            } catch (IOException e1) {
                System.out.println("正在等待");
            }
            try {
                mailReceive.receive(pack);
                address = pack.getAddress();
                System.out.println("客户的地址:" + address);
            } catch (IOException e) {}
            if (address != null) {
                new ServerThread2(address).start();
            }
        }
    }
}

class ServerThread2 extends Thread {
    InetAddress address;
    DataOutputStream out = null;
    DataInputStream in = null;
    String s = null;
    ServerThread2(InetAddress address) {
        this.address = address;
    }

    @Override
    public void run() {
        FileInputStream in;
        byte b[] = new byte[8192];
        try {
            in = new FileInputStream("earth.png");
            int n = -1;
            while ((n = in.read(b)) != -1) {
                DatagramPacket data = new DatagramPacket(b, n, address, 5678);
                DatagramSocket mailSend = new DatagramSocket();
                mailSend.send(data);
            }
            in.close();
            byte end[] = "end".getBytes();
            DatagramPacket data = new DatagramPacket(end, end.length, address, 5678);
            DatagramSocket mailSend = new DatagramSocket();
            mailSend.send(data);
        } catch (Exception e ){

        }
    }
}
