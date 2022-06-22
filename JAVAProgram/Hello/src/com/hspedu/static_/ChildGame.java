package com.hspedu.static_;

public class ChildGame {
    public static void main(String[] args) {
        Child child1 = new Child("白骨精");
        child1.payFee(200.0);
        child1.showFee();
        Child child2 = new Child("蜘蛛精");
        child2.payFee(600.0);
        child2.showFee();

        Child.payFee(400.0);
        Child.showFee();


    }
}

class Child {
    private String name;

    private static double fee = 0.0;

    public Child(String name) {
        this.name = name;
    }

    public static void payFee(double fee) {
        Child.fee += fee;
    }
    public void join() {

    }
    public static void showFee() {
        System.out.println("fee = " + Child.fee);
    }
}
