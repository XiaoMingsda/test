package com.hspedu.homework;

import java.util.Arrays;

public class Homework10 {
    public static void main(String[] args) {
        //方法1
        StringBuilder ss = new StringBuilder("abc");
        reverse(ss);
        System.out.println(ss);

        String string = new String("abcdef");
        try {
            string = reverse(string,1,string.length() - 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(string);
    }

    public static void reverse(StringBuilder sb) {
        /*int mid = (1 + (sb.length() - 2)) / 2;
        for (int i = 1; i <= mid; i++) {
            char ch1 = sb.charAt(i);
            char ch2 = sb.charAt(sb.length() - 1 - i);
            sb.replace(i,i+1,ch2 + "");
            sb.replace(sb.length() - 1 - i,sb.length() - i,ch1 + "");

        }*/
        for (int begin = 1, end = sb.length() - 2; begin < end; begin++, end--) {
            char ch1 = sb.charAt(begin);
            char ch2 = sb.charAt(end);

            sb.replace(begin, begin + 1, ch2 + "");
            sb.replace(end, end + 1, ch1 + "");
        }
    }

    public static String reverse(String string, int start, int end) {

        //对输入的参数做一个验证
        //(1) 写出正确的情况
        //(2) 然后取反即可
        if (!(string != null && start >= 0 && end > start && end < string.length())) {
            throw new RuntimeException("输入格式有误");
        }
        char[] chars = string.toCharArray();
        for (int i = start, j = end; i < j; i++, j--) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }
}
