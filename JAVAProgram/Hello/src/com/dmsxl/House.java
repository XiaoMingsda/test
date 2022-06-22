package com.dmsxl;

import sun.awt.windows.ThemeReader;

public class House implements Runnable{
    int waterAmount;
    public void setWaterAmount (int w) {
        waterAmount = w;
    }
    @Override
    public void run() {
        while (true) {
            String name = Thread.currentThread().getName();
            if ("狗".equals(name)) {
                System.out.println(name + "喝水");
                waterAmount = waterAmount - 2;
            }
            else if ("猫".equals(name)) {
                System.out.println(name + "喝水");
                waterAmount = waterAmount - 1;
            }
            System.out.println(" 剩 " + waterAmount + " 现在 " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            if (waterAmount <= 0) {
                return;
            }
        }
    }
}
