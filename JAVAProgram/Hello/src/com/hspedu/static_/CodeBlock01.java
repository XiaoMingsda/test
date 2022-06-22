package com.hspedu.static_;

public class CodeBlock01 {
    public static void main(String[] args) {
        //Movie movie = new Movie("你好，李焕英");
       //A a = new A();
        //BBB bbb = new BBB();
        CCC ccc = new CCC();
    }
}

class Movie {
    private String name;
    private double money;

    {
        System.out.println("电影开始");
        System.out.println("电影播放");
        System.out.println("电影结束");
    };
    public Movie(String name) {
        System.out.println("调用一个参数构造函数");
        this.name = name;
    }

    public Movie(String name, double money) {
        this.name = name;
        this.money = money;
    }
}

class A{
    private int n2 = getN2();

    {
        System.out.println("普通代码块被执行");
    };

    private static int n1 = getN1();

    static {
        System.out.println("静态代码块被执行");
    };

    public static int getN1() {
        System.out.println("静态方法被执行");
        return 100;
    }

    public int getN2() {
        System.out.println("普通方法被执行");
        return 200;
    }

    public A() {
        System.out.println("构造器被执行");
    }
}

class AAA {
    {
        System.out.println("AAA类的普通代码块被执行");
    };
    public AAA() {
        //super()
        //本类的普通代码块和普通属性的初始化(具体看定义的顺序)
        System.out.println("AAA类的构造器被执行");
    }
}

class BBB extends AAA {
    {
        System.out.println("BBB类的普通代码块被执行");
    };

    public BBB() {
        //super()
        //本类的普通代码块和普通属性的初始化(具体看定义的顺序)
        System.out.println("BBB类的构造器被执行");
    }
}

class CCC {
    {
        System.out.println("CCC类的普通代码块被执行");
    };

    private int a = getA();

    public int getA() {
        System.out.println("getA普通方法被执行");
        return 100;
    }

    public CCC() {
        System.out.println("CCC类的构造器被执行");
    }
}
