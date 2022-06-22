package com.hspedu.enum_;

public class EnumMethod {
    public static void main(String[] args) {
        //使用Season2 枚举类，来使用各种方法
        //Season2.AUTUMN为一个枚举类对象，本质还是一个对象(结合反编译结果来理解)，
        //因此可以直接赋值
        Season2 autumn = Season2.AUTUMN;

        //输出枚举对象的名称
        System.out.println(autumn.name());

        //ordinal() 输出的是该枚举对象的次序/编号，从0开始编号
        //AUTUMN枚举对象是第三个，因此输出2
        System.out.println(autumn.ordinal());

        //从反编译可以看到 values方法， 返回 Season2[]
        //含有定义的所有枚举对象
        Season2[] values = Season2.values();
        for (Season2 season: values) {//增强for循环
            System.out.print(season + " ");//按照toString()方法输出
        }
        System.out.println();

        //valuesOf: 将字符串转换成枚举对象，要求字符串必须为已有的常量名，否则报异常
        //执行流程
        //1. 根据你输入的"AUTUMN" 到 Season2的枚举对象去查找
        //2. 如果找到了，就返回，如果没有找到，就报错
        Season2 autumn1 = Season2.valueOf("AUTUMN");
        System.out.println("autumn1=" + autumn1);//返回值为枚举对象
        System.out.println(autumn == autumn1);

        //Season2.AUTUMN的编号[2] - Season2.SUMMER的编号[1]
        //可以看一下Enum类实现compareTo()的代码
        System.out.println(Season2.AUTUMN.compareTo(Season2.SUMMER));
    }
}
