package com.hspedu.homework;

public class Homework05 {
    public static void main(String[] args) {
        A a = new A();
        a.hi();
    }
}

class A {
    private final String NAME = "名字2";
    public void hi() {
        class B {
            private final String NAME = "名字1";

            public void showName() {
                System.out.println("B NAME = " + NAME + "A name = " + A.this.NAME);
            }
        }

        B b = new B();
        b.showName();
    }
}