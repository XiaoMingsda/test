package com.hspedu.date_;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Date01 {
    public static void main(String[] args) throws ParseException {
        // 1.
        //1. 获取当前系统时间
        //2. 这里的Date 类是在java.util包
        //3. 默认输出的日期格式是国外的方式，因此通常需要对格式进行转化
        Date d1 = new Date();//获取当前系统时间
        System.out.println("当前日期=" + d1);

        //3.
        Date d2 = new Date(9234567); //通过指定毫秒数得到时间
        System.out.println("d2=" + d2);

        //2.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss E");
        String format = sdf.format(d1); // format()将日期转换成指定格式的字符串
        System.out.println("当前日期=" + format);

        //4.
        //1. 可以把一个格式化的字符串转为对应的Date
        //2. 得到Date 仍然在输出时，还是按照国外的形式，如果希望指定格式输出，需要转换
        //3. 在把String -> Date，使用的 sdf 格式需要和你给的String的格式一样，否则会
        //   抛出转换异常(即使你已经在抛出了异常)
        String s = "1996年01月01日 10:20:30 星期一";
        Date parse =sdf.parse(s); //会产生转换异常 ParseException 使用throws抛出即可
        System.out.println("parse=" + parse);
    }
}
