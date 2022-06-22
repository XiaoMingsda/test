package com.hspedu.wrapper_;

public class WrapperExercise01 {
    public static void main(String[] args) {
        Double d = 100d;
        Float f = 1.5f;
        System.out.println(d + " " + f);

        //三元运算符[是一个整体，要提高到最高精度]
        Object obj1 = true ? new Integer(1) : new Double(2.0);
        System.out.println(obj1);

        Object obj2;
        if (true)
            obj2 = new Integer(1);
        else
            obj2 = new Double(2.0);
        System.out.println(obj2);
    }
}