package com.hspedu.innerclass;

public class AnonymousInnerClassDetail {
    public static void main(String[] args) {
        Outer05 outer05 = new Outer05();
        outer05.f1();
    }
}

class Outer05 {
    private int n1 = 99;
    public void f1() {
        //创建一个基于类的匿名内部类
         Person p = new Person() {
            @Override
            public void hi() {
                System.out.println("匿名内部类重写了 hi方法");
            }
        };
        System.out.println("匿名内部类的运行类型:" + p.getClass());
         p.hi();//动态绑定，运行类型是 Outer05$1

        //也可以直接调用，匿名内部类本身也是返回对象
        new Person() {
            @Override
            public void hi() {
                System.out.println("匿名内部类重写了 hi方法,哈哈");
            }
        }.hi();
        //当然，也可以这样写
        new Person().hi();//调用父类的方法
    }
}
class Person {//类
    public void hi() {
        System.out.println("Person hi()");
    }
}
//抽象类/接口
