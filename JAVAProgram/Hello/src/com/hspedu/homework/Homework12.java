package com.hspedu.homework;

public class Homework12 {
    public static void main(String[] args) {
        printName("Han shun Ping");
    }

    public static void printName(String str) {
        if (str == null) {
            System.out.println("str 不能为空");
            return;
        }

        String[] name = str.split(" ");
        if (name.length != 3) {
            System.out.println("输入的字符串格式不对");
            return;
        }

        System.out.println(String.format("%s,%s .%c",name[2],name[0],name[1].toUpperCase().charAt(0)));
    }
}
