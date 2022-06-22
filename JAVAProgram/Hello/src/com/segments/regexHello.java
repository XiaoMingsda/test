package com.segments;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.util.Scanner;

public class regexHello {
    public static void main(String[] args) {
        /*
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String regex = "[a-zA-Z|0-9|_]+";
        if (s.matches(regex)) {
            System.out.println(s + "由字母/数字/下划线组成");
        } else {
            System.out.println(s + "不由字母/数字/下划线组成");
        }
         */

        /* 利用Scanner的next()、hasNext()方法返回文件里的单词
        File file = null;
        Scanner sc = null;
        try {
            file = new File("E:\\JavaFile", "Hello1.txt");
            sc = new Scanner(file);
            while (sc.hasNext()) {
                String word = sc.next();
                System.out.print(word + " ");
            }
        } catch (Exception e) {
            System.out.println("error");
        }
         */

        /* 使用Scanner的nextInt()/nextDouble()方法返回数字单词
        File file = null;
        Scanner sc = null;
        int total = 0;
        try {
            file = new File("E:\\JavaFile", "Hello1.txt");
            sc = new Scanner(file);
            while (sc.hasNext()) {
                try {
                    int price = sc.nextInt();
                    total += price;
                    System.out.println("price = " + price);
                } catch (Exception ee) {
                    String s = sc.next();
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        System.out.println("total = " + total);
         */
        String s = "数学98 语文99 英语98";
        Scanner sc = new Scanner(s);
        sc.useDelimiter("[^0123456789.]+");
        int total = 0;
        while (sc.hasNext()) {
            try {
                double score = sc.nextDouble();
                total += score;
            } catch (Exception e) {
                String ss = sc.next();
            }
        }
        System.out.println("total = " + total);
    }
}
