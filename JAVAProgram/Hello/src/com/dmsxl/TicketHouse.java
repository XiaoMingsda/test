package com.dmsxl;

public class TicketHouse implements Runnable{
    int fiveAmount = 2, tenAmount = 0, twentyAmount = 0;
    @Override
    public void run() {
        if ("张飞".equals(Thread.currentThread().getName())) {
            saleTicked(20);
        }
        else if ("李逵".equals(Thread.currentThread().getName())) {
            saleTicked(5);
        }
    }
    private synchronized void saleTicked(int money) {
        if (money == 5) {
            fiveAmount = fiveAmount + 1;
            System.out.println("给" + Thread.currentThread().getName() + "入场券," +
                    Thread.currentThread().getName() + "的钱正好");
        }
        else if (money == 20) {
            while (fiveAmount < 3) {
                try {
                    System.out.println("\n" + Thread.currentThread().getName() + "靠边等...");
                    wait();
                    System.out.println("\n" + Thread.currentThread().getName() + "继续买票");
                } catch (InterruptedException e) {

                }
            }
            fiveAmount = fiveAmount - 3;
            twentyAmount = twentyAmount + 1;
            System.out.println("给" + Thread.currentThread().getName() + "入场券," +
                    Thread.currentThread().getName() + "给20,找赎15元");
        }
            notifyAll();
    }

}
