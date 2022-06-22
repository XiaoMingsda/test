package com.exp.exp4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadURLSource {
    public static void main(String[] args) {
        new NetWin();
    }
}

class NetWin extends JFrame implements ActionListener, Runnable {
    JButton button;
    URL url;
    JTextField inputURLText;
    JTextArea area;
    byte b[] = new byte[118];
    Thread thread;
    NetWin() {
        inputURLText = new JTextField(20);
        area = new JTextArea(12, 12);
        button = new JButton("确定");
        button.addActionListener(this);
        thread = new Thread(this);
        JPanel p = new JPanel();
        p.add(new JLabel("输入网址："));
        p.add(inputURLText);//输入网址的文本框
        p.add(button);
        add(area, BorderLayout.CENTER);//下方文本框
        add(p, BorderLayout.NORTH);//上方内容
        setBounds(60, 60, 560, 300);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //点击'确定'按钮时，如果线程已经销毁，则再创建一个线程
        if (!(thread.isAlive()))
            thread = new Thread(this);
            System.out.println(2);
        try {
            thread.start();
        } catch (Exception ee) {
            inputURLText.setText("我正在读取" + url);
        }
    }
    public void run() {
        try {
            int n = -1;
            area.setText(null);
            String name = inputURLText.getText().trim();
            URL url = new URL(name);
            String hostName = url.getHost();
            int urlPortNumber = url.getPort();
            String fileName = url.getFile();
            InputStream in = url.openStream();
            area.append("\n主机：" + hostName + "端口：" + urlPortNumber +
                        "包含的文件名字：" + fileName);
            area.append("\n文件的内容如下：");
            while ((n = in.read(b)) != -1) {
                String s = new String(b, 0, n);
                area.append(s);
            }
        } catch (MalformedURLException e1) {
            inputURLText.setText("" + e1);
            return;
        }
        catch (IOException e1) {
            inputURLText.setText("" + e1);
            return;
        }
    }
}