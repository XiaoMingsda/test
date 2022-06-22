package com.hspedu.collection_;

import java.util.ArrayList;
import java.util.List;

public class CollectionMethod {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        //由于Collection 接口本身不能被实例化，因此采用实现Collection接口的类ArrayList示例
        List list = new ArrayList();
        //add:添加单个元素
        list.add("jack");
        list.add(10);//有自动装箱的过程，转为Integer 本质是 list.add(new Integer(10))
        list.add(true);
        System.out.println("list=" + list); //此时，list里的内容均为对象

        //remove:删除指定元素 若元素重复出现，则删除首次出现的元素
        list.remove(0);//删除第一个元素
        //list.remove(true);
        //list.remove((Integer)10);
        System.out.println("list=" + list);

        //contains:查找元素是否存在
        System.out.println(list.contains(true));

        //size:获取元素个数
        System.out.println(list.size());

        //isEmpty:判断是否为空
        System.out.println(list.isEmpty());

        //clear:清空
        //list.clear();
        //System.out.println("list=" + list);

        //addAll:添加多个元素
        ArrayList list2 = new ArrayList();
        list2.add("红楼梦");
        list2.add("三国演义");
        list.addAll(list2);
        System.out.println("list=" + list);

        //containsAll:查找多个元素是否都存在
        System.out.println(list.containsAll(list2));

        //removeAll:删除多个元素
        list.removeAll(list2);
        System.out.println("list=" + list);
    }
}
