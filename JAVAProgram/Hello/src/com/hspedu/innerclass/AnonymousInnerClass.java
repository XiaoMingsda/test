package com.hspedu.innerclass;

/**
* 演示匿名内部类的使用
* */
public class AnonymousInnerClass {
    public static void main(String[] args) {
        Outer04 outer04 = new Outer04();
        outer04.method();
    }
}

class Outer04 {//外部类
    private int n1 = 10;

    public void method() {
        //基于接口的匿名内部类
        //1.需求：想使用IA接口，并创建对象
        //2.传统方式，是写一个类，实现该接口，并创建对象
        /*
        IA tiger = new Tiger(); //接口的引用可以指向实现该接口的应用实例
        tiger.cry();
        */
        //3.现在的需求是：实现这个接口的类只是使用一次，后面再不使用
        //4.可以使用匿名内部类来简化开发
        //5.tiger的编译类型 IA
        //6.tiger的运行类型 匿名内部类 XXXX => Outer04$1
        /*
        * 我们看底层 会分配 类名 将XXXX换为Outer04$1
        * 等号右边：jdk底层在创建匿名内部类 Outer04$1，立即马上就创建了 Outer04$1实例，
        * class XXXX implements IA {
        *   @Override
            public void cry() {
                System.out.println("老虎叫唤");
            }
        * }
        * 并且
        * 等号左边：把地址返回给 tiger(涉及到new关键字)
        * */
        //8.匿名内部类使用一次，就不能再使用 也就是说 Outer04$1 使用一次就消失了
        //   但tiger是一个对象，依然可以再次使用
        /*
        IA tiger = new IA() {
            @Override
            public void cry() {
                System.out.println("老虎叫唤");
            }
        };
        */
        //验证一下，打印tiger的运行类型(用getClass()方法实现)
        /*
        System.out.println("tiger的运行类型=" + tiger.getClass());
        tiger.cry();
        */

        //演示基于类的匿名内部类
        //分析
        //1.father编译类型 Father
        //2.father运行类型 Outer04$2
        //   如果等号右边不加{}号，那么运行类型就是 Father
        //3.底层会创建匿名内部类
        /*
        * class Outer04$2 extends Father {
        *   @Override
            public void test() {
                System.out.println("匿名内部类重写了test方法");
            }
        * }
        * */
        //4.同时也直接返回了 匿名内部类 Outer04$2的对象
        Father father = new Father("jack") {
            @Override
            public void test() {
                System.out.println("匿名内部类重写了test方法");
            }
        };

        System.out.println("father对象的运行类型=" + father.getClass()); //Outer04$2
        father.test();

        //基于抽象类的匿名内部类
        Animal animal = new Animal() {
            @Override
            public void eat() {
                System.out.println("小狗吃骨头");
            }
        };
        animal.eat();
    }
}
/*

interface IA {//接口，默认是abstract，不能被实例化
    public void cry();
}
*/

/*
class Tiger implements IA {
    @Override
    public void cry() {
        System.out.println("老虎叫唤");
    }
}
*/

class Father {//类
    public Father(String name) {//构造器

    }
    public void test() {//方法

    }
}

abstract class Animal {
    abstract public void eat();
}