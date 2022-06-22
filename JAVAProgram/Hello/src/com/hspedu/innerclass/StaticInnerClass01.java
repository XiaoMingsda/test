package com.hspedu.innerclass;

/**
 * 演示静态内部类的使用
 *
 * */
public class StaticInnerClass01 {
    public static void main(String[] args) {
        Outer10 outer10 = new Outer10();
        outer10.m1();

        //外部其他类访问静态内部类
        //方法一
        Outer10.Inner10 inner10 = new Outer10.Inner10();
        inner10.say();
        //方法二
        Outer10.Inner10 inner101 = outer10.getInstance();
        inner101.say();
        //方法三
        Outer10.Inner10 inner102 = Outer10.getInstance1();
        inner102.say();
    }
}

class Outer10 {//外部类
    private int n1 = 10;
    private static String name = "张三";
    //Inner10就是静态内部类
    //1. 放在外部类的成员位置
    //2. 使用static 修饰
    //3. 可以直接访问外部类的所有静态成员，包含私有的，但不能直接访问非静态成员
    //4. 可以添加任意访问修饰符(public、protected、默认、private)，因为它的地位就是一个成员
    //5. 作用域：同其他的成员，为整个类体
    static class Inner10 {
        private static String name = "韩顺平教育";
        public void say() {
            //如果外部类和静态内部类的成员重名时，静态内部类访问的时候，
            //默认遵循就近原则，如果想访问外部类的成员，则可以使用 (外部类名.成员)
            System.out.println(name + " 外部类name= " + Outer10.name);
        }
    }

    public void m1() {
        Inner10 inner10 = new Inner10();
        inner10.say();
    }

    //方法二
    public Inner10 getInstance() {
        return new Inner10();
    }

    //方法三
    public static Inner10 getInstance1() {
        return new Inner10();
    }
}
