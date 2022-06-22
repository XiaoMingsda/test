package com.hspedu.list_;

import java.util.*;

public class ListFor {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {

        //List接口的实现子类 Vector LinkedList
        List list = new ArrayList();
        //List list = new Vector();
        //List list = new LinkedList();
        list.add("jack");
        list.add("tom");
        list.add("鱼香肉丝");
        list.add("北京烤鸭子");

        //遍历
        //1. 迭代器
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println("obj=" + obj);
        }

        //增强for
        for (Object obj : list) {
            System.out.println("obj=" + obj);
        }

        //传统for
        for (int i = 0; i < list.size(); i++) {
            System.out.println("obj=" + list.get(i));
        }
    }
}
