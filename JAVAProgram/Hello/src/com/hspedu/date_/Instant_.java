package com.hspedu.date_;

import java.time.Instant;
import java.util.Date;

public class Instant_ {
    public static void main(String[] args) {

        //1. 通过静态方法now()获取表示当前时间戳的对象
        Instant now = Instant.now();
        System.out.println(now);

        //2. 通过 from 可以把 Instant转成Date
        Date date = Date.from(now);
        System.out.println(date);
        //3. 通过date.toInstant()可以把date转成Instant对象
        Instant instant = date.toInstant();
        System.out.println(instant);
    }
}
