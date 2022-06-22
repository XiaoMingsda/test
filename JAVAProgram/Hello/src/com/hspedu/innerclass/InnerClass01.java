package com.hspedu.innerclass;

public class InnerClass01 { //外部其他类
    public static void main(String[] args) {

    }
}

class Outer { //外部类
    private int n1 = 100; //属性
    //方法
    public void m1() {
        System.out.println("m1()");
    }
    //构造器
    public Outer(int n1) {
        this.n1 = n1;
    }
    //代码块
    {
        System.out.println("代码块...");
    };
    //内部类，在Outer类的内部
    class Inner {

    }
}
