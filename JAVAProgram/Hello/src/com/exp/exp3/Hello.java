package com.exp.exp3;

import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

public class Hello {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        for (int i = 0; i < 10; i++) {
            int m = list.get(i);
            System.out.printf("%3d", m);
        }
    }
}