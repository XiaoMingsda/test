package com.exp.exp5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientGuess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Socket mySocket = null;
        DataInputStream inData = null;
        DataOutputStream outData = null;
        Thread thread;
        ReadNumber readNumber = null;
        try {
            mySocket = new Socket();
            readNumber = new ReadNumber();
            thread = new Thread(readNumber);
            System.out.print("输入服务器的IP:");
            String IP = scanner.nextLine();
            System.out.println("输入端口号:");
            int port = scanner.nextInt();
            if (mySocket.isConnected()){}
            else {
                InetAddress address = InetAddress.getByName(IP);
                InetSocketAddress socketAddress = new InetSocketAddress(address, port);
                mySocket.connect(socketAddress);
                InputStream inputStream = mySocket.getInputStream();
                OutputStream outputStream = mySocket.getOutputStream();
                inData = new DataInputStream(inputStream);
                outData = new DataOutputStream(outputStream);
                readNumber.setIn(inData);
                readNumber.setOut(outData);
                thread.start();
            }
        } catch (Exception e ) {
            System.out.println("服务器已断开" + e);
        }
    }
}

class ReadNumber implements Runnable {
    Scanner scanner = new Scanner(System.in);
    DataInputStream in;
    DataOutputStream out;

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        try {
            out.writeUTF("Y");
            while (true) {
                String str = in.readUTF();
                System.out.println(str);
                if (!str.startsWith("询问")) {
                    if (str.startsWith("猜对了")) continue;
                    System.out.print("好的， 我输入猜测：");
                    int myGuess = scanner.nextInt();
                    String enter = scanner.nextLine();
                    out.writeInt(myGuess);
                } else {
                    System.out.print("好的，我输入Y或N:");
                    String myAnswer = scanner.nextLine();
                    out.writeUTF(myAnswer);
                    if (myAnswer.startsWith("N")) return;
                }
            }
        } catch (Exception e ){
            System.out.println("与服务器断开" + e );
            return;
        }
    }
}
