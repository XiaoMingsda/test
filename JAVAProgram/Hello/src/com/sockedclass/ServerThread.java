package com.sockedclass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket socket;
    DataOutputStream out = null;
    DataInputStream in = null;
    ServerThread(Socket t) {
        socket = t;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                double r = in.readDouble();
                double area = Math.PI * r * r;
                out.writeDouble(area);
            } catch (Exception e) {
                System.out.println("kehulikai");
                return;
            }
        }
    }
}
