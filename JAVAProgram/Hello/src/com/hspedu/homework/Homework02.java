package com.hspedu.homework;

public class Homework02 {
    public static void main(String[] args) {
        System.out.println(Frock.getNextNum());
        System.out.println(Frock.getNextNum());

        Frock frock = new Frock();
        Frock frock1 = new Frock();
        Frock frock2 = new Frock();
        System.out.println(frock.getSerialNumber());
        System.out.println(frock1.getSerialNumber());
        System.out.println(frock2.getSerialNumber());
    }
}

class Frock {
    private static int currentNum = 100000; //衣服出厂的序列号起始值
    private int serialNumber;

    public static int getNextNum() {//生成上衣唯一序列号的方法
        Frock.currentNum += 100;
        return Frock.currentNum;
    }

    public Frock() {
        this.serialNumber = Frock.getNextNum();
    }

    public int getSerialNumber() {
        return serialNumber;
    }
}