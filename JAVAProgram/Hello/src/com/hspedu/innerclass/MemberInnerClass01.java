package com.hspedu.innerclass;

/**
 * 演示成员内部类的使用
 *
 * */
public class MemberInnerClass01 {
    public static void main(String[] args) {
        Outer08 outer08 = new Outer08();
        outer08.t1();

        //外部其他类，使用成员内部类的三种方式
        //第一种方式：
        //outer08.new Inter08(); 相当于把 new Inter08()当做是outer08成员。
        // 如果成员内部类的修饰符为private，则这种方法不行。
        //这就是一个语法，不要特别的纠结。
        Outer08.Inter08 inter08 = outer08.new Inter08();
        inter08.say();

        //第二种方式：在外部类中，编写一个方法，可以返回Inner08对象
        Outer08.Inter08 inter08Instance = outer08.getInner08Instance();
        inter08Instance.say();
    }
}

class Outer08 {//外部类
    private int n1 = 10;
    public String name = "张三";

    private void hi() {
        System.out.println("hi()方法");
    }
    //1.注意：成员内部类，是定义在外部类的成员位置上
    //2.可以添加任意访问修饰符(public、protected、默认、private)，因为它的地位就是一个成员
     class Inter08 {//成员内部类
        private double sal = 99.1;
        public void say() {
            //3.可以直接访问外部类的所有成员(属性和方法)，包含私有的
            System.out.println("Outer01 的 n1 = " + n1 + " outer01 的 name = "
                                + Outer08.this.n1);
            hi();
        }
    }

    //第二种方法
    public Inter08 getInner08Instance() {
        return new Inter08();
    }

    //如何使用成员内部类
    //写方法
    public void t1() {
        //4.使用成员内部类
        //创建成员内部类的对象，然后使用相关的属性和方法(即便成员内部类里的属性和方法为private，
        // 仍然可以访问，因为他们在同一个类里)
        Inter08 inter08 = new Inter08();
        inter08.say();
        System.out.println("访问成员内部类的私有属性：" + inter08.sal);
    }
}
