package com.hspedu.interface_;

public class InterfaceExercise01 {
    public static void main(String[] args) {
        //B b = new B();
        //System.out.println(b.a);
        System.out.println(IA1.a);
        System.out.println(B.a);
    }
}

interface IA1 {
    int a = 23;
}
class B implements IA1 {
}
