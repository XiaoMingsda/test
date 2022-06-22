package com.hspedu.list_;

import java.util.*;

public class VectorSource {
    public static void main(String[] args) {
        List list = new Vector();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.add(100);

        Object[] arr1 = {1,2,3};
        Object[] arr2 = Arrays.copyOf(arr1,10);
        System.out.println(Arrays.toString(arr2));
    }
}
