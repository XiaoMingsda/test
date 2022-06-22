package com.hspedu.bignum_;

import java.math.BigInteger;

public class BigInteger_ {
    public static void main(String[] args) {
        //当我们编程中，需要处理很大的整数，long 不够用
        //可以使用BigInteger的类来搞定
//        long l = 2222222222222222222222222l;
//        System.out.println("l = " + l);

        BigInteger bigInteger = new BigInteger("2222222222222222222222222");
        BigInteger bigInteger1 = new BigInteger("100");
        System.out.println(bigInteger);

        //在对BigInteger 进行加减乘除的时候，需要使用对应的方法，不能直接使用运算符进行计算
        //可以创建一个要操作的BigInteger然后进行相应操作
        BigInteger add = bigInteger.add(bigInteger1);
        System.out.println(add);
        BigInteger subtract = bigInteger.subtract(bigInteger1);
        System.out.println(subtract);
        BigInteger multiply = bigInteger.multiply(bigInteger1);
        System.out.println(multiply);
        BigInteger divide = bigInteger.divide(bigInteger1);
        System.out.println(divide);
    }
}
