package com.exp.exp2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class MainClass {
    public static void main(String[] args) {
        Sky sky = new Sky();
        JFrame jFrame = new JFrame();
        jFrame.add(sky);
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setBackground(Color.white);
    }
}

class Earth extends JLabel implements ActionListener {
    JLabel moon;
    Timer timer;
    double pointX[] = new double[360], pointY[] = new double[360];
    int w = 200, h = 200, i = 0;
    Earth() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(w, h));
        timer = new Timer(20, this);
        setIcon(new ImageIcon("earth.png"));
        setHorizontalAlignment(SwingConstants.CENTER);
        moon = new JLabel(new ImageIcon("moon.png"), SwingConstants.CENTER);
        add(moon);
        moon.setPreferredSize(new Dimension(60, 60));
        pointX[0] = 0;
        pointY[0] = h / 2;
        double angle = 1 * Math.PI / 180;
        for (int i = 0; i < 359; i++) {
            pointX[i + 1] = pointX[i] * Math.cos(angle) - Math.sin(angle) * pointY[i];
            pointY[i + 1] = pointY[i] * Math.cos(angle) + pointX[i] * Math.sin(angle);
        }
        for (int i = 0; i < 360; i++) {
            pointX[i] = 0.8 * pointX[i] + w / 2;
            pointY[i] = 0.8 * pointY[i] + h / 2;
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        i = (i + 1) % 360;
        moon.setLocation((int)pointX[i] - 30, (int)pointY[i] - 30);
    }
}

class Sky extends JLabel implements ActionListener {
    Earth earth;
    Timer timer;
    double pointX[] = new double[360], pointY[] = new double[360];
    int w = 400, h = 400, i = 0;
    Sky() {
        setLayout(new FlowLayout());
        timer = new Timer(100, this);
        setPreferredSize(new Dimension(w, h));
        earth = new Earth();
        add(earth);
        earth.setPreferredSize(new Dimension(200, 200));
        pointX[0] = 0;
        pointY[0] = h / 2;
        double angle = 1 * Math.PI / 180;
        for (int i = 0; i < 359; i++) {
            pointX[i + 1] = pointX[i] * Math.cos(angle) - Math.sin(angle) * pointY[i];
            pointY[i + 1] = pointY[i] * Math.cos(angle) + pointX[i] * Math.sin(angle);
        }
        for (int i = 0; i < 360; i++) {
            pointX[i] = 0.5 * pointX[i] + w / 2;
            pointY[i] = 0.5 * pointY[i] + h / 2;
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        i = (i + 1) % 360;
        earth.setLocation((int)pointX[i] - 100, (int)pointY[i] - 100);
    }
}