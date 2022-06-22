package com.sockedclass;

import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket you = null;
        ServerThread serverThread;
        while (true) {
            try {
                serverSocket = new ServerSocket(2010);
            } catch (Exception e) {
                System.out.println("zhengzaijianting");
            }
            try {
                System.out.println("dengdaikehuhujiao");
                you = serverSocket.accept();
                System.out.println("kehudedizhi:" + you.getInetAddress());
            } catch (Exception e) {
                System.out.println("zhengzaidengdaikehu");
            }
            if (you != null) {
                new ServerThread(you).start();
            }
        }
    }
}
