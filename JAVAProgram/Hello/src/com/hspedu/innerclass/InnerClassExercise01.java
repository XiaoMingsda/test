package com.hspedu.innerclass;

public class InnerClassExercise01 {
    public static void main(String[] args) {
        //当做实参直接传递，简洁高效
        f1(new Il() {
            @Override
            public void show() {
                System.out.println("这是一副名画");
            }
        });

        CellPhone cellPhone = new CellPhone();
        cellPhone.alarmclock(new Bell() {
            @Override
            public void ring() {
                System.out.println("懒猪起床了");
            }
        });
        cellPhone.alarmclock(new Bell() {
            @Override
            public void ring() {
                System.out.println("小伙伴上课了");
            }
        });
    }

    //静态方法,形参是接口类型
    public static void f1(Il il) {
        il.show();
    }
}

//接口
interface Il {
    void show();
}

interface Bell {
    void ring();
}

class CellPhone {
    public void alarmclock(Bell bell) {
        System.out.println("参数的类名：" + bell.getClass());
        bell.ring();
    }
}