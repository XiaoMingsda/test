package com.exp.exp5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientGetImage extends JFrame implements Runnable, ActionListener {
    JButton b = new JButton("获取图像");
    ImageCanvas canvas;
    ClientGetImage() {
        super("I am a client");
        setSize(800, 800);
        setVisible(true);
        b.addActionListener(this);
        add(b, BorderLayout.NORTH);
        canvas = new ImageCanvas();
        add(canvas, BorderLayout.CENTER);
        Thread thread = new Thread(this);
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thread.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        byte b[] = "请发图像".trim().getBytes();
        try {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            DatagramPacket data = new DatagramPacket(b, b.length, address, 1234);
            DatagramSocket mailSend = new DatagramSocket();
            mailSend.send(data);
        } catch (Exception e){

        }
    }

    @Override
    public void run() {
        DatagramPacket pack = null;
        DatagramSocket mailReceive = null;
        byte b[] = new byte[8192];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            pack = new DatagramPacket(b, b.length);
            mailReceive = new DatagramSocket(5678);
        } catch (Exception e) {

        }
        try {
            while(true) {
                mailReceive.receive(pack);
                String message = new String(pack.getData(), 0, pack.getLength());
                if (message.startsWith("end")) {
                    break;
                }
                out.write(pack.getData(), 0, pack.getLength());
            }
            byte imagebyte[] = out.toByteArray();
            out.close();
            Toolkit tool = getToolkit();
            Image image = tool.createImage(imagebyte);
            canvas.setImage(image);
            canvas.repaint();
            validate();
        } catch (IOException e){

        }
    }

    public static void main(String[] args) {
        new ClientGetImage();
    }
}
