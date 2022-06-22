package com.hspedu.homework;

import jdk.nashorn.internal.ir.CallNode;

public class Homework06 {
    public static void main(String[] args) {
        Person tangsen = new Person("唐僧",VehiclesFactory.getBoat());
        tangsen.common();
        tangsen.passRiver();
        tangsen.common();
        tangsen.passRiver();
        tangsen.fly();
        tangsen.common();
        tangsen.passRiver();
    }
}

interface Vehicles {
    void work();
}

class Horse implements Vehicles {
    @Override
    public void work() {
        System.out.println("用马工作");
    }
}
class Boat implements Vehicles {
    @Override
    public void work() {
        System.out.println("用船工作");
    }
}
class Plane implements Vehicles {
    @Override
    public void work() {
        System.out.println("用飞机工作");
    }
}
class VehiclesFactory {
    private static Horse horse = new Horse();//只有一匹马

    private VehiclesFactory() {}//构造器私有化
    public static Horse getHorse() {
        return horse;
    }
    public static Boat getBoat() {
        return new Boat();
    }
    public static Plane getPlane() {
        return new Plane();
    }
}

class Person {
    private String name;
    private Vehicles vehicles;

    public Person(String name, Vehicles vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }

    public void fly() {
        if (!(vehicles instanceof Plane)) {
            vehicles = VehiclesFactory.getPlane();
        }
        vehicles.work();
    }
    public void passRiver() {
        if (!(vehicles instanceof Boat)) {
            vehicles = VehiclesFactory.getBoat();
        }
        vehicles.work();
    }
    public void common() {
        if (!(vehicles instanceof Horse)) {
            vehicles = VehiclesFactory.getHorse();
        }
        vehicles.work();
    }
}