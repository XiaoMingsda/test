package com.hspedu.homework;

public class Homework03 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.shout();

        Dog dog = new Dog();
        dog.shout();

        cc cc = new cc();
        cc.h1();
    }
}

abstract class Animal {
    public abstract void shout();
}

class cc {
    public void h1() {
        Animal animal = new Animal(){
            @Override
            public void shout() {
                System.out.println("动物在叫");
            }
        };
        animal.shout();
    }
}
class Cat extends Animal {
    @Override
    public void shout() {
        System.out.println("猫会喵喵叫");
    }
}
class Dog extends Animal {
    @Override
    public void shout() {
        System.out.println("狗会汪汪叫");
    }
}
