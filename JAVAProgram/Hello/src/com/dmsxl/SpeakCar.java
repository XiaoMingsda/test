package com.dmsxl;

public class SpeakCar implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <= 20000; i++) {
            System.out.println("轿车" + i + " ");
        }
    }
}
