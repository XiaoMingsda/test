package com.exp.exp6;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerUI2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ServerUI");
        ServerUI serverUI = new ServerUI();
        frame.setContentPane(serverUI.getRoot());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);
        //frame.pack();
        frame.setVisible(true);

        //窗口关闭事件
        //https://blog.csdn.net/zhenshiyiqie/article/details/8440806
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
//加入动作
//
                System.out.println("窗口关闭");
                serverUI.closeResource();
            }

        });
    }
}
