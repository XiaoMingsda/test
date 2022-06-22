package com.atguigu.test;

import java.lang.reflect.Method;

public class UserServletTest {
    public void login() {
        System.out.println("login方法");
    }
    public void regist() {
        System.out.println("regist方法");
    }
    public static void main(String[] args) {
        String action = "login";
        try {
            //获得action对应的方法
            Method method = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(method);
            //调用对应的方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
