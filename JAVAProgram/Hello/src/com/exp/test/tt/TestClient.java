package com.exp.test.tt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TestClient extends JFrame {
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private String response = "";
    private JTextArea jTextArea;

    public void init(){
        setSize(800,600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Client");
        setLayout(null);
        setBackground(Color.WHITE);

        Font font = new Font(null,Font.BOLD,16);

        try {
            socket = new Socket("localhost",8888);
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jTextArea = new JTextArea();
        jTextArea.setBounds(0,0,800,500);
        jTextArea.setFont(font);

        add(jTextArea);

        response = IOUtil.readString(is);
        jTextArea.setText(response);

        JTextField jTextField = new JTextField();
        jTextField.setFont(font);
        jTextField.setBounds(0,500,800,50);
        add(jTextField);
        jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jTextField.getText();
                jTextField.setText("");
                IOUtil.writeString(os,text);
            }
        });

        setVisible(true);

        recive();

    }

    public void recive(){
        while (true){
            String s = IOUtil.readString(is);
            response += "\n"+s;
            jTextArea.setText(response);
        }
    }

    public static void main(String[] args) {
        TestClient t = new TestClient();
        t.init();
    }
}
