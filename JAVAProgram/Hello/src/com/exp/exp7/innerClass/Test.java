package com.exp.exp7.innerClass;

public class Test {
    public static void main(String[] args) {
        MyOuterClass myOuterClass = new MyOuterClass();
        MyOuterClass.MyInnerClass myInnerClass = myOuterClass.new MyInnerClass();
        myInnerClass.TestInner();
        myOuterClass.testOut();
    }
}

//成员内部类
class MyOuterClass {
    private String outValue = "外部类字符串的值";
    class MyInnerClass {
        public void TestInner() {
            System.out.println(outValue);
        }
    }

    public void testOut() {
        MyInnerClass myInnerClass = new MyInnerClass();
        myInnerClass.TestInner();
    }
}
