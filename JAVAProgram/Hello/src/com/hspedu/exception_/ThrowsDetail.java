package com.hspedu.exception_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ThrowsDetail {
    public static void main(String[] args) {
        f2();//由于f2()的异常属于运行时异常，因此对于f2()、main()都有默认的throws方式
    }

    public static void f2() {
        //1. 对于编译异常，程序中必须处理，比如 try-catch 或者 throws
        //2. 对于运行时异常，程序中如果没有处理，默认就是throws的方式处理
        int n1 = 10;
        int n2 = 0;
        double res = n1 / n2;
    }

    public static void f1() throws FileNotFoundException{
        //由于此处的异常为编译时异常，没有默认的throws方式。
        //因此，需要使用throws抛出该异常 或 使用try-catch 处理该异常
        FileInputStream fis = new FileInputStream("d://aa.txt");
    }
    public static void f3() throws FileNotFoundException{
        //f3() 调用 f1()，由于f1() 抛出了一个编译时异常，相当于现在
        //f3() 也有了一个编译时异常，因此需要使用 throws 或 try-catch
        f1();
    }

    public static void f4() {
        //在f4() 调用 f5()，由于f5()抛出一个运行时异常，而运行时异常有默认的
        //throws方式，因此不会报错误
        f5();
    }
    public static void f5() throws ArithmeticException{

    }
}

class Father { //父类
    public void method() throws RuntimeException {
    }
}

class Son extends Father {//子类
    //3. 子类重写父类的方法时，对抛出异常的规定：子类重写的方法，
    //   所抛出的异常类型要么和父类抛出的异常一致，要么为父类抛出的异常类型的
    //   子类型(即不能扩大范围)
    //4. 在throws过程中，如果有方法 try-catch，就相当于处理异常，就可以不必throws
    @Override
    public void method() throws NullPointerException {
        super.method();
    }
}