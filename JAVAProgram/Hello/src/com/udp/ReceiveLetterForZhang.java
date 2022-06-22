package com.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveLetterForZhang implements Runnable{
    @Override
    public void run() {
        DatagramPacket packet = null;
        DatagramSocket socket = null;
        byte[] b = new byte[8192];
        try {
            packet = new DatagramPacket(b, b.length);
            socket = new DatagramSocket(888);
        } catch (Exception e) {

        }
        while (true) {
            if (socket == null) break;
            else {
                try {
                    socket.receive(packet);
                    String message = new String(packet.getData(), 0, packet.getLength());
                    System.out.printf("%25s\n", "receive:" + message);
                } catch(Exception e){

                }
            }
        }
    }
}
