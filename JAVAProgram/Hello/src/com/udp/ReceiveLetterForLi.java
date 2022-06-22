package com.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveLetterForLi implements Runnable{
    @Override
    public void run() {
        DatagramPacket datagramPacket = null;
        DatagramSocket datagramSocket = null;
        byte[] b = new byte[8192];
        try {
            datagramPacket = new DatagramPacket(b, b.length);
            datagramSocket = new DatagramSocket(666);
        } catch (Exception e) {

        }
        while (true) {
            try {
                datagramSocket.receive(datagramPacket);
                String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.printf("%25s\n", "receive:" + message);
            } catch (Exception e) {

            }
        }
    }
}
