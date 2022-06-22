package com.dmsxl;

import sun.awt.windows.ThemeReader;

public class demo1 {
    public static void main(String[] args) {
        /*Daemon daemon = new Daemon();
        daemon.A.start();
        daemon.B.setDaemon(true);
        daemon.B.start();*/
        System.out.println(test.count);
        new Thread(new test()).start();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(test.count);
        /*
        WindowTime windowTime = new WindowTime();
        windowTime.setTitle("计时器");
         */
        /*
        SpeakCar speakCar = new SpeakCar();
        Thread t = new Thread(speakCar);
        System.out.println(t.isAlive());
         */
        /*
        win win = new win();
         */
        /*
        WindowTyped windowTyped = new WindowTyped();
        windowTyped.setTitle("打字母游戏");
        windowTyped.setSleepTime(3000);
         */
        /*
        ThreadJoin threadJoin = new ThreadJoin();
        Thread customer = new Thread(threadJoin);
        Thread cakeMaker = new Thread(threadJoin);
        customer.setName("顾客");
        cakeMaker.setName("蛋糕");
        threadJoin.setJoinThread(cakeMaker);
        customer.start();
         */
        /*
        TicketHouse ticketHouse = new TicketHouse();
        Thread zhangfei, likui;
        zhangfei = new Thread(ticketHouse);
        zhangfei.setName("张飞");
        likui = new Thread(ticketHouse);
        likui.setName("李逵");
        zhangfei.start();
        likui.start();
         */
        /*
        Bank bank = new Bank();
        bank.setMoney(200);
        Thread accountant = new Thread(bank);
        Thread cashier = new Thread(bank);
        accountant.setName("会计");
        cashier.setName("出纳");
        accountant.start();
        cashier.start();
         */
        /*
        Home home = new Home();
        Thread thread = new Thread(home);
        thread.start();
        for (int i = 0; i < 2000; i++) {
            System.out.println("1");
        }
         */
        /*
        ClassRoom classRoom = new ClassRoom();
        classRoom.student.start();
        classRoom.teacher.start();
         */
        /*
        House house = new House();
        house.setWaterAmount(10);
        Thread dog;
        dog = new Thread(house);
        Thread cat;
        cat = new Thread(house);
        dog.setName("狗");
        cat.setName("猫");
        dog.start();
        cat.start();
        while (true) {
            System.out.println("2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
        }
         */
        /*
        SpeakCar speakCar = new SpeakCar();
        SpeakElephant speakElephant = new SpeakElephant();
        Thread elephant;
        elephant = new Thread(speakElephant);
        Thread car;
        car = new Thread(speakCar);
        elephant.start();
        car.start();
        for (int i = 1; i <= 20000; i++) {
            System.out.println("现在 " + i + " ");
        }

         */
    }
}

class test implements Runnable{
    public static int count = 0;

    @Override
    public void run() {
        count++;
    }
}