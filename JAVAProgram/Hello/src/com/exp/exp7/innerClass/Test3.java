package com.exp.exp7.innerClass;

public class Test3 {
    public static void main(String[] args) {
        LocalInner localInner = new LocalInner();
        localInner.testOut(10);
    }
}
class LocalInner {
    private int outValue1;
    private static int outValue2;
    public void testOut(final int j) {
        final int i = 10;
        int k = 1;
        class MyInnerClass {
            private String inValue;
            public void TestInner() {
                System.out.println(outValue1);
                System.out.println(outValue2);
                System.out.println(inValue);
                System.out.println(i);
                System.out.println(j);
                System.out.println(k);
            }
        }
        MyInnerClass myInnerClass = new MyInnerClass();
        myInnerClass.TestInner();
    }
}