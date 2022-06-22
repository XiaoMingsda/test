package com.hspedu.pkg;


import java.util.Arrays;

public class Java20220125 {
    public static void main (String[] args) {
        AA aa = new AA("小明", 18, '男');
    }
}

class AA {
    private String name;
    private int age;
    private char gender;

    public AA(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof AA) {
            AA object = (AA) anObject;
            return ((name.equals(object.name)) && (age == object.age)
                    && (gender == object.gender));
        }
        return false;
    }

    @Override
    public String toString() {
        return "AA{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
