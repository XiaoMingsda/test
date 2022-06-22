package com.hspedu.set_;

import java.util.HashSet;

public class HashSet01 {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();

        //说明
        //1. 在执行add方法后，会返回一个boolean值
        //2. 如果添加成功，返回true，否则返回false
        //3. 可以通过 remove 指定删除哪个对象
        System.out.println(hashSet.add("john"));//T
        System.out.println(hashSet.add("lucy"));//T
        System.out.println(hashSet.add("john"));//F
        System.out.println(hashSet.add("jack"));//T
        System.out.println(hashSet.add("Rose"));//T


        hashSet.remove("john");
        System.out.println("hashSet=" + hashSet);


        hashSet = new HashSet();
        System.out.println("hashSet=" + hashSet);
        //4. HashSet 不能添加相同的元素/数据?
        hashSet.add("lucy");//添加成功
        hashSet.add("lucy");//加入不了
        hashSet.add(new Dog("tom"));//OK
        hashSet.add(new Dog("tom"));//OK
        System.out.println("hashSet=" + hashSet);

        //在加深一下，非常经典的面试题.
        //看源码，做分析
        hashSet.add(new String("hsp"));//OK
        hashSet.add(new String("hsp"));//加入不了.
    }
}

class Dog {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
