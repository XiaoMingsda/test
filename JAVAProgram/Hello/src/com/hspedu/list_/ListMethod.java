package com.hspedu.list_;

import java.util.ArrayList;
import java.util.List;

public class ListMethod {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("张三丰");
        list.add("贾宝玉");
        //void add(int index, Object ele):在index位置插入ele元素
        //在index = 1 的位置插入一个对象
        list.add(1, "韩顺平");
        System.out.println("list=" + list);

        //boolean addAll(int index, Collection eles):从index位置开始将eles中的
        //所有元素添加进来
        List list2 = new ArrayList();
        list2.add("jack");
        list2.add("tom");
        list.addAll(1, list2);
        System.out.println("list=" + list);

        //Object get(int index):获取指定index位置的元素
        System.out.println("索引为3的元素 " + list.get(3));

        //int indexOf(Object obj):返回obj在集合中首次出现的位置
        System.out.println("tom在集合中首次出现的位置 " + list.indexOf("tom"));

        //int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置
        list.add("韩顺平");
        System.out.println("list=" + list);
        System.out.println("韩顺平在集合中最后一次出现的位置 " + list.lastIndexOf("韩顺平"));

        //Object remove(int index):移除指定index位置的元素，并返回此元素
        System.out.println("删除索引为0的元素 " + list.remove(0));
        System.out.println("list=" + list);

        //Object set(int index, Object ele):设置指定index位置的元素为ele，相当于是替换.
        //index必须在定义的范围之内，不能越界
        System.out.println("替换索引为1的元素 " + list.set(1, "玛丽"));
        System.out.println("list=" + list);

        //List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的子集合
        List returnList = list.subList(0,2);
        System.out.println("returnList=" + returnList);
    }
}
