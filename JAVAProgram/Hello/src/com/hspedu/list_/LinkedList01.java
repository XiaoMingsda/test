package com.hspedu.list_;

public class LinkedList01 {
    public static void main(String[] args) {
        //模拟一个简单的双向链表

        Node jack = new Node("jack");
        Node tom = new Node("tom");
        Node hsp = new Node("老韩");

        //连接三个结点，形成双向链表
        //jack->tom->hsp
        jack.next = tom;
        tom.next = hsp;
        //hsp->tom->jack
        hsp.pre = tom;
        tom.pre = jack;

        Node first = jack;//让first引用指向jack,就是双向链表的头结点
        Node last = hsp;//让last引用指向hsp,就是双向链表的尾结点

        //演示，从头到尾进行遍历
        System.out.println("从头到尾进行遍历");
        while (true) {
            if (first == null) {
                break;
            }
            //输出first 信息
            System.out.println(first);
            first = first.next;
        }

        //演示，从尾到头的遍历
        while (true) {
            if (last == null) {
                break;
            }
            //输出last 信息
            System.out.println(last);
            last = last.pre;
        }

        //演示链表的添加对象/数据，是多么方便
        //要求：tom ---------- hsp直接插入一个对象 smith

        //1. 先创建一个Node节点，name 就是 smith
        Node smith = new Node("smith");

        smith.next = tom.next;
        smith.pre = hsp.pre;
        tom.next = smith;
        hsp.pre = smith;

        first = jack;
        while (true) {
            if (first == null) {
                break;
            }
            System.out.println(first);
            first = first.next;
        }
    }
}

//定义一个Node类，Node对象 表示双向链表的一个节点
class Node{
    public Object item;//真正存放数据
    public Node next;//指向后一个节点
    public Node pre;//指向前一个节点

    public Node(Object item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Node name=" + item;
    }
}
