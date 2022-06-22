package com.hspedu.homework;

public class Homework04 {
    public static void main(String[] args) {
        Cellphone cellphone = new Cellphone();
        cellphone.testWork();

        new Cal(){
            @Override
            public void work() {
                System.out.println("工作");
            }
        }.work();

        cellphone.testWork2(new Cal() {
            @Override
            public void work() {
                System.out.println("工作2");
            }
        });
    }
}

interface Cal {
    void work();
}

class Cellphone {
    public void testWork() {
         Cal cal1 = new Cal() {
            @Override
            public void work() {
                System.out.println("计算器工作");
            }
        };
         cal1.work();
    }
    public void testWork2(Cal cal) {
        cal.work();
    }
}