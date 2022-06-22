package com.hspedu.exception_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Throws01 {
    public static void main(String[] args) {

    }

    public static void f1() throws FileNotFoundException,NullPointerException,ArithmeticException{
        //try {
            //创建了一个文件流对象
            //1. 这里的异常是一个FileNotFoundException 编译异常
            //2. 使用前面讲过的 try-catch-finally
            //3. 使用throws，抛出异常，让调用f1方法的调用者(方法)处理
            //4. throws后面的异常类型可以是方法中产生的异常类型，也可以是它的父类
            //5. throws关键字后也可以是异常列表，即可以抛出多个异常
            FileInputStream fis = new FileInputStream("d://aa.txt");
       // } catch (FileNotFoundException e) {
            //System.out.println(e.getMessage());
        //}
    }
}
