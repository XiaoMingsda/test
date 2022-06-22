package com.dmsxl;

public class Bank implements Runnable{
    int money = 200;
    public void setMoney(int m) {
        money = m;
    }
    @Override
    public void run() {
        if ("会计".equals(Thread.currentThread().getName())) {
            saveOrTake(300);
        }
        else if ("出纳".equals(Thread.currentThread().getName())) {
            saveOrTake(100);
        }
    }
    public synchronized void saveOrTake(int m) {
        if ("会计".equals(Thread.currentThread().getName())) {
            System.out.println(Thread.currentThread().getName() + "开始工作");
            for (int i = 0; i < 3; i++) {
                money += m / 3;
                System.out.println(Thread.currentThread().getName() + "存入" +
                        m / 3 + "元，还剩" + money + "元");
                try {
                    System.out.println(Thread.currentThread().getName() + "休息一下");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
            System.out.println(Thread.currentThread().getName() + "工作完成");
        }
        else if ("出纳".equals(Thread.currentThread().getName())) {
            System.out.println(Thread.currentThread().getName() + "开始工作");
            for (int i = 0; i < 3; i++) {
                money -= m / 3;
                System.out.println(Thread.currentThread().getName() + "出纳" +
                        m / 3 + "元，还剩" + money + "元");
                try {
                    System.out.println(Thread.currentThread().getName() + "休息一下");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
            System.out.println(Thread.currentThread().getName() + "工作完成");
        }
    }
}
