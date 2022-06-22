package com.hspedu.innerclass;

/**
  * 演示局部内部类的使用
 * 相当于局部变量，不过类型为类而已
* */
public class LocalInnerClass {
    public static void main(String[] args) {
        Outer02 outer02 = new Outer02();
        outer02.m1();
    }
}

class Outer02 {
    private int n1 = 100;
    private void m2() {//私有方法
        System.out.println("Outer02 m2()");
    }
    public void m1() {//方法
        //1.局部内部类是定义在外部类的局部位置，通常在方法
        //3.不能添加访问修饰符，但是可以使用final修饰(类似局部变量，不能用public 等
        //   访问修饰符修饰，但是可以用final 修饰，代表它不能被继承(若是继承，也只能是在
        //   作用域内继承))
        //4.作用域：仅仅在定义它的方法或代码块中
        final class Inner02 {//局部内部类(本质仍然是一个类)
            //2.可以直接访问外部类的所有成员，包含私有的(属性或方法)
            private int n1 = 800;
            public void f1() {
                //5.局部内部类可以访问外部类的成员
                //7.如果外部类和局部内部类的成员重名时，默认遵循就近原则，如果想访问外部类
                //   的成员，使用外部类名.this.成员 去访问
                //System.out.println("n1=" + n1);结果输出800
                //Outer02.this 本质就是外部类的对象
                System.out.println("外部类的n1=" + Outer02.this.n1);
                m2();
            }
        }
        //6.外部类在方法中，可以创建Inner02对象，然后调用方法即可
        Inner02 inner02 = new Inner02();
        inner02.f1();
    }
}


