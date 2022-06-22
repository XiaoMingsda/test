package com.dmsxl;

public class SpeakElephant implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <= 20000; i++) {
            System.out.println("大象" + i + " ");
        }
    }
}
