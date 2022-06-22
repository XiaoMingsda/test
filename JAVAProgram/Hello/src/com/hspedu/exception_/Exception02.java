package com.hspedu.exception_;

import java.util.Scanner;

public class Exception02 {
    public static void main(String[] args) {
        try {
            String name = null;
            System.out.println(name.length());
            System.out.println("123");
            int num1 = 10;
            int num2 = 0;
            System.out.println(num1 / num2);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (ArithmeticException e) {
            //e.printStackTrace();
            System.out.println("123");
        }
    }
}
