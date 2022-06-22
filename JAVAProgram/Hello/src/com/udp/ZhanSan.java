package com.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class ZhanSan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Thread thread;
        ReceiveLetterForZhang receiveLetterForZhang = new ReceiveLetterForZhang();
        try {
            thread = new Thread(receiveLetterForZhang);
            thread.start();
            byte[] b = new byte[1];
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            DatagramPacket datagramPacket = new DatagramPacket(b, b.length, inetAddress, 666);
            DatagramSocket datagramSocket = new DatagramSocket();
            System.out.print("to LiSi：");
            while (scanner.hasNext()) {
                String mess = scanner.nextLine();
                b = mess.getBytes();
                if (mess.length() == 0) {
                    System.exit(0);
                }
                //b = mess.getBytes();
                datagramPacket.setData(b);
                datagramSocket.send(datagramPacket);
                System.out.print("sending：");
            }
        } catch (Exception e) {

        }
    }
}
