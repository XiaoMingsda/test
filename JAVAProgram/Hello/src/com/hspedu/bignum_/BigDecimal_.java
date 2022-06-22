package com.hspedu.bignum_;

import java.math.BigDecimal;

public class BigDecimal_ {
    public static void main(String[] args) {
        //当我们需要保存一个精度很高的数时，double 不够用
        //可以是 BigDecimal
        double d = 1999.11111111111111111111111111111111111111111111d;
        System.out.println(d);
        BigDecimal bigDecimal = new BigDecimal("1999.11111111111111111111111111111111111111111111");
        BigDecimal bigDecimal1 = new BigDecimal("12.3");
        System.out.println(bigDecimal);

        //如果对BigDecimal进行运算，例如加减乘除，不能直接使用运算符
        //需要创建一个需要操作的 BigDecimal 然后调用相应的方法即可
        System.out.println(bigDecimal.add(bigDecimal1));
        System.out.println(bigDecimal.subtract(bigDecimal1));
        System.out.println(bigDecimal.multiply(bigDecimal1));

        //System.out.println(bigDecimal.divide(bigDecimal1));
        //可能抛出算术异常(可能除不尽)
        //解决方案
        //在调用divide()方法时，指定精度即可，BigDecimal.ROUND_CEILING
        //如果有无限循环小数，就会保留分子的精度(即调用divide()方法的那个对象)
        System.out.println(bigDecimal.divide(bigDecimal1,BigDecimal.ROUND_CEILING));
    }
}
