package com.hspedu.arrays_;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;

public class ArraysMethod02 {
    public static void main(String[] args) {
        Integer[] arr = {1,2,90,123,567};
        // binarySearch 通过二分搜索法进行查找，要求必须排好
        //1. 使用 binarySearch 二叉查找
        //2. 要求该数组是有序的(升序只能升序)，如果该数组是无序的，不能使用binarySearch
        //3. 如果数组中不存在该元素，则
        // return -(low + 1);  // key not found. (low表示应该在的位置)
        int index = Arrays.binarySearch(arr, 568);
        System.out.println("index=" + index);

        //copyOf 数组元素的复制
        //1. 从arr数组中，拷贝 arr.length个元素到 newArr数组中
        //2. 如果拷贝的长度 > arr.length 就在新数组的后面增加null
        //3. 如果拷贝长度 < 0 就抛出异常NegativeArraySizeException
        //4. 该方法的底层使用的是 System.arraycopy()
        Integer[] newArr = Arrays.copyOf(arr,arr.length);
        //Integer[] newArr = Arrays.copyOf(arr,arr.length + 1);
        //Integer[] newArr = Arrays.copyOf(arr,-1);
        System.out.println("==拷贝执行完毕后==");
        System.out.println(Arrays.toString(newArr));

        //fill 数组元素的填充
        //1. 使用99去填充num数组，可以理解成是替换原来的元素
        Integer[] num = new Integer[]{9,3,2};
        Arrays.fill(num, 99);
        System.out.println("==num数组填充后==");
        System.out.println(Arrays.toString(num));

        //equals 比较两个数组元素内容是否完全一致
        Integer[] arr2 = {1,2,90,123,567};
        //1. 如果arr 和 arr2 数组的元素一样，则方法true;
        //2. 如果不完全一样，就返回 false
        boolean equals = Arrays.equals(arr,arr2);
        System.out.println("equals=" + equals);

        //asList 将一组值，转换成list
        //1. asList方法，会将 (2,3,4,5,6,1)数据转成一个List集合
        //2. 返回的 asList 编译类型 List(接口)
        //3. 运行类型 java.util.Arrays$ArrayList，是Arrays类的
        //   静态内部类 private static class ArrayList<E> extends AbstractList<E>
        //            implements RandomAccess, java.io.Serializable
        List list = Arrays.asList(2,3,4,5,6,1);
        System.out.println(list.getClass());
    }
}
