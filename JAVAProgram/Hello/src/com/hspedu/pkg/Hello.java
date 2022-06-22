package com.hspedu.pkg;

import java.util.Arrays;

public class Hello {
    public static void main(String[] args) {
        Master master = new Master();
       /*
        Cat cat = new Cat();
        Rice rice = new Rice();
        master.feed(cat, rice);

        Dog dog = new Dog();
        Bone bone = new Bone();
        master.feed(dog, bone);*/
        Animal animal = new Cat();
        Food food = new Rice();
        master.feed(animal, food);

       // Cat cat = (Cat) animal;
        //cat.catchMouse();
        System.out.println(animal.name);

        Base base = new A();
        System.out.println(base.getCount1());
        System.out.println(base.getCount3());
    }
}

class Base {
    int count = 10;

    public int getCount1() {
        return this.getCount2() + 10;
    }
    public int getCount2() {
        return count;
    }
    public int getCount3() {
        return this.count;
    }
}

class A extends Base {
    int count = 20;
    public int getCount2() {
        return count;
    }
}
