package com.hspedu.set_;

import java.util.*;

public class SetMethod {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        //1. 以Set接口的实现类HashSet来讲解Set接口的方法
        //2. set接口的实现类的对象(Set接口对象),不能存放重复的元素，可以添加一个null
        //3. set接口对象存放数据是无序(即添加的顺序和取出的顺序不一致)
        //4. 注意：取出的顺序虽然不是添加的顺序，但是它的位置是固定的.
        Set set = new HashSet();
        set.add("john");
        set.add("lucy");
        set.add("john");//重复
        set.add("jack");
        set.add("hsp");
        set.add("mary");
        set.add(null);
        set.add(null);//再次添加null
        for (int i = 0; i < 10; i++) {
            System.out.println("set=" + set);
        }

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            System.out.println("o=" + o);
        }

        for (Object o : set) {
            System.out.println("o=" + o);
        }

        Set set2 = new HashSet();
        set2 = set;
        System.out.println(set2);
        System.out.println(set.equals(set2));
    }
}
