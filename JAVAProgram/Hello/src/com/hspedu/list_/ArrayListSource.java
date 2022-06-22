package com.hspedu.list_;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all"})
public class ArrayListSource {
    public static void main(String[] args) {
        //List list = new ArrayList();
        List list = new ArrayList(1);
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        for (int i = 11; i <= 15; i++) {
            list.add(i);
        }
        list.add(100);
        list.add(200);
        list.add(null);
    }
}
