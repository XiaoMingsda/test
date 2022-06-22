package com.hspedu.wrapper_;

public class WrapperVSString {
    public static void main(String[] args) {
        //包装类(Integer) -> String
        Integer i = 100; //自动装箱
        //方式1
        String str1 = i + "";
        //方式2
        String str2 = i.toString();
        //方式3
        String str3 = String.valueOf(i);

        //String -> 包装类(Integer)
        String str4 = "12345";
        Integer i2 = Integer.parseInt(str4);//Integer.parseInt()返回一个
                                            //int类型数据，用到了自动装箱
        Integer i3 = new Integer(str4);//构造器

        Integer i4 = 1;
        Integer i5 = 128;

        Integer i11 = 127;
        int i12 = 127;
        System.out.println(i11 == i12);

        Integer i13 = 128;
        int i14 = 129;
        System.out.println(i13 == i14);
    }
}
