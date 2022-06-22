package com.hspedu.exception_;

public class CustomException {
    public static void main(String[] args) {
        // 将自定义异常继承自Exception 编译时异常，则需要手动抛出一个自定义异常
        // public static void main(String[] args) throws AgeException{
        try {
            f1(9);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("再回首");
    }

    public static void f1(int age) {
        if (!(age >= 18 && age <= 120)) {
            throw new AgeException("年龄需要在 18~120之间");
        }
    }
}

//自定义一个异常
//1. 一般情况下，我们自定义异常是继承 RuntimeException
//2. 即把自定义异常做成 运行时异常，好处是，我们可以使用默认的处理机制
//3. 即比较方便
class AgeException extends RuntimeException {
    // 将自定义异常继承自Exception 编译时异常
    // class AgeException extends Exception {
    public AgeException(String message) {//构造器
        super(message);
    }
}