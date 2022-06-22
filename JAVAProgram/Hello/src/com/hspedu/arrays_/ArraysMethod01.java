package com.hspedu.arrays_;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysMethod01 {
    public static void main(String[] args) {
        Integer[] integers = {1,20,90};
        //遍历数组
//        for (int i = 0; i < integers.length; i++) {
//            System.out.println(integers[i]);
//        }
        //直接使用Arrays.toString方法，显示数组
        System.out.println(Arrays.toString(integers));

        //演示sort方法的使用
        Integer[] arr = {1,-1,7,0,89};
        //进行排序
        //1. 可以直接使用冒泡排序，也可以直接使用Arrays提供的sort方法排序
        //2. 因为数组是引用类型，所以通过sort排序后，会直接影响到实参arr
        //3. sort重载的，也可以通过传入一个接口 Comparator 实现定制排序
        Arrays.sort(arr);
        System.out.println("===排序后===");
        System.out.println(Arrays.toString(arr));
        //定制排序：参数1 需要排序的数组；参数2 实现了Comparator的接口(重写compare方法)
        Integer[] arr1 = {1,-1,7,0,23};
        //Comparator接口为泛型，但由于还没学到泛型，所以这里把泛型取消
        Arrays.sort(arr1, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                //Object类无法比较大小，可以把它转为包装类
                int value1 = (Integer)o1;
                int value2 = (Integer)02;
                return value1 - value2;
            }
        });
        System.out.println(Arrays.toString(arr1));
        //为了方便理解，这里以一个冒泡排序为例
        int[] arr2 = {-1,-5,10,15,5};
        bubble(arr2, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
            //return o1 - o2; 类型为Object，不能比较大小，可以转换为包装类
                int value1 = (Integer)o1;
                int value2 = (Integer)o2;
                return value1 - value2;
            }
        });
        System.out.println(Arrays.toString(arr2));
    }

    public static void bubble (int[] arr, Comparator c) {
        int tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (c.compare(arr[j], arr[j+1]) > 0) {
                    tmp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = tmp;
                }
            }
        }
        System.out.println("排序结果如下：");
    }
}
