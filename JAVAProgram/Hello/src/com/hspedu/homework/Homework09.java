package com.hspedu.homework;

public class Homework09 {
    public static void main(String[] args) {
        try {
            args[4] = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
