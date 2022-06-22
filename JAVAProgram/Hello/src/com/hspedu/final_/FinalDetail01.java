package com.hspedu.final_;

public class FinalDetail01 {
    public static void main(String[] args) {

    }
}

class AA {
    //final关键字
    private final double TAX_RATE = 99.9;
    private final double TAX_RATE2;
    private final double TAX_RATE3;

    //static + final 关键字
    private static final double TAX_RATE4 = 3.2;
    private static final double TAX_RATE5;

    //在代码块里赋值
    {
        TAX_RATE2 = 2.0;
    };
    //在构造器里赋值，但要注意每个构造器都要能给final修饰的变量赋值
    public AA(double tmp) {
        TAX_RATE3 = tmp;
    }
    public AA() {
        TAX_RATE3 = 3.0;
    }

    //只能在定义时初始化或在静态代码块里赋值
    static {
      TAX_RATE5 = 2.3;
    };
}
