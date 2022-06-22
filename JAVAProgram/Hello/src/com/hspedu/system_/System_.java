package com.hspedu.system_;

import java.util.Arrays;

public class System_ {
    public static void main(String[] args) {
        //exit 退出当前程序
//        System.out.println("ok1");
        //1. exit(0) 表示程序退出
        //2. 0 表示一个状态，正常的状态
//        System.exit(0);
//        System.out.println("ok2");

        //arraycopy
        int[] arr1 = {1,2,3};
        int[] arr2 = new int[3]; //[0,0,0]
        System.arraycopy(arr1,0,arr2,0,arr1.length);
        System.out.println(Arrays.toString(arr2));

        //currentTimeMillens
        //返回当前时间距离1970-1-1的毫秒数
        System.out.println(System.currentTimeMillis());

    }
}
