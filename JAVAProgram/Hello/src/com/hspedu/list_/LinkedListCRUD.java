package com.hspedu.list_;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListCRUD {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        System.out.println("linkedList=" + linkedList);

        //演示一个删除节点的
        linkedList.remove(); //这里默认删除的是第一个结点
        //linkedList.remove(2); //删除索引为2的节点
        System.out.println("linkedList=" + linkedList);

        //修改某个结点对象
        linkedList.set(1,999);
        System.out.println("linkedList=" + linkedList);

        //得到某个结点对象
        //get(1) 是得到双向链表的第二个对象
        Object o = linkedList.get(1);
        System.out.println(o);

        //遍历方式
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println("next=" + next);
        }

        for (Object obj : linkedList) {
            System.out.println("obj=" + obj);
        }

        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println("i=" + linkedList.get(i));
        }
    }
}
