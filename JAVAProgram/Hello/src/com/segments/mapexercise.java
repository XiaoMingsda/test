package com.segments;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class mapexercise {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap();
        map.put("ASD",map.get("ASD") + 1);
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            Object val = map.get(key);
            System.out.println(key + " " + val);
        }
    }
}
