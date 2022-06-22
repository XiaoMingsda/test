package com.hspedu.abstract_;

//测试模板设计模式
public class TestTemplate {
    public static void main(String[] args) {
        AA aa = new AA();
        aa.calculateTime();
        BB bb = new BB();
        bb.calculateTime();
    }
}

class AA extends Template {
    @Override
    public void job() {
        long num = 0;
        for (long i = 0; i < 1000000; i++) {
            num += i;
        }
    }
}

class BB extends Template {
    @Override
    public void job() {
        long num = 0;
        for (long i = 0; i < 5000000; i++) {
            num += i;
        }
    }
}
