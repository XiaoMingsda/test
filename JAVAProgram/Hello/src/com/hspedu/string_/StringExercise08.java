package com.hspedu.string_;

public class StringExercise08 {
    public static void main(String[] args) {
        /*
        //类型1
        String c = "hello" + "abc";//先将字符串拼接，再返回地址引用
        */


        //类型2
        String a = "hello"; //创建 a对象
        String b = "abc"; //创建 b对象
        //1. 先创建一个 StringBuilder sb = new StringBuilder()
        //2. 执行 sb.append("hello")
        //3. sb.append("abc")
        //4. String c = sb.toString()
        //最后其实是 c 指向堆中的对象(String) value[] -> 池中的 "helloabc"
        String c = a + b;


        /*
        //类型3 同类型2执行过程相同
        String a = "hello";
        String c = a + "abc";
        */
    }
}
