package com.hspedu.list_;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListExercise {
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add("hello" + i);
        }

        list.add(2,"韩顺平教育");
        System.out.println("第5个元素 " + list.get(4));
        System.out.println("删除第6个元素 " + list.remove(5));
        list.set(6,"修改第7个元素");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println("obj=" + obj);
        }
    }
}
