package com.exp.exp7.innerClass;

public class Test2 {
    public static void main(String[] args) {
        /*静态内部类举例1
        MyOuterClass2.MyStaticInnerClass inner = new MyOuterClass2.MyStaticInnerClass();
        inner.TestInner();
        MyOuterClass2 myOuterClass2 = new MyOuterClass2();
        myOuterClass2.testOut();
        */

        //静态内部类举例2
        //定义静态内部类
        MyOuterClass3.MyStaticInnerClass innerClass = new MyOuterClass3.MyStaticInnerClass();
        innerClass.TestInner1();
        //定义成员内部类
        MyOuterClass3 myOuterClass3 = new MyOuterClass3();
        MyOuterClass3.MyInnerClass myInnerClass = myOuterClass3.new MyInnerClass();
        myInnerClass.TestInner3();
    }
}

//静态内部类举例1
class MyOuterClass2 {
    public String outValue;
    static class MyStaticInnerClass {
        String inValue;
        public void TestInner() {System.out.println("AAA");}
    }
    public void testOut() {
        MyStaticInnerClass myStaticInnerClass = new MyStaticInnerClass();
        myStaticInnerClass.TestInner();
    }
}
//静态内部类举例2
class MyOuterClass3 {
    public String outValue1 = "outValue1";
    public static String outValue2 = "outValue2";
    //静态内部类
    static class MyStaticInnerClass {
        String inValue;
        public void TestInner1() {
            //静态内部类只能访问静态变量
            System.out.println(outValue2);
        }
        //静态内部类可以定义静态方法
        public static void TestInner2() {}
    }
    //成员内部类
    class MyInnerClass {
        String inValue;
        public void TestInner3() {
            //成员内部类可以访问外嵌类的静态成员和非静态成员
            System.out.println(outValue1);
            System.out.println(outValue2);
        }
        /* 成员内部类不能定义静态方法
        public static void TestInner4() {

        }*/
    }

    public void testOut() {
        MyStaticInnerClass myStaticInnerClass = new MyStaticInnerClass();
        myStaticInnerClass.TestInner1();
    }
}


