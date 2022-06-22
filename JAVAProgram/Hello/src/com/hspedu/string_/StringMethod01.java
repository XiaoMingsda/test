package com.hspedu.string_;

public class StringMethod01 {
    public static void main(String[] args) {
        //1. equals 前面已经讲过了，比较内容是否相同，区分大小写
        String str1 = "hello";
        String str2 = "Hello";
        System.out.println(str1.equals(str2));

        //2. equalsIgnoreCase 忽略大小写的判断内容是否相等
        String username = "johN";
        if ("john".equalsIgnoreCase(username)) {
            System.out.println("Success!");
        } else {
            System.out.println("Failure!");
        }
        //3. length 获取字符的个数，字符串的长度
        System.out.println("韩顺平".length());
        //4. indexOf 获取字符在字符串对象中第一次出现的索引，索引从0开始，如果找不到，返回-1
        String s1 = "wer@terwe@g";
        int index = s1.indexOf('@');
        System.out.println(index);
        System.out.println(s1.indexOf("we"));
        //5. lastIndexOf 获取字符在字符串中最后一次出现的索引，索引从0开始，如果找不到，返回-1
        s1 = "wer@terwe@g@";
        index = s1.lastIndexOf('@');
        System.out.println(index);
        //6. subString 截取指定范围的子串
        String name = "hello,张三";
        //下面name.subString(6) 从索引6开始截取所有的内容
        System.out.println(name.substring(6));//截取后面的字符
        //从索引2开始，截取到索引为5的位置(不包括5，即[2,5))
        System.out.println(name.substring(2,5));
    }
}
