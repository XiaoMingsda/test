package com.hspedu.collection_;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Collection_ {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        //1. 集合主要是两组(单列集合，双列集合)
        //2. Collection 接口有两个重要的子接口 List set，他们的实现子类都是单列集合(存放一个对象)
        //3. Map接口的实现子类 是双列集合，存放的 K-V
        //Collection 接口
        //Map 接口
        ArrayList arrayList = new ArrayList();
        arrayList.add("jack");
        arrayList.add("tom");
        HashMap hashMap = new HashMap();
        hashMap.put("NO1","北京");
        hashMap.put("NO2","上海");
    }
}
