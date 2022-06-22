package com.hspedu.homework;

public class Homework11 {
    public static void main(String[] args) {
        try {
            register(null,"123456","180852@qq.com");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void register(String name, String pwd, String email) {
        if (!(name != null && pwd != null && email != null)) {
            throw new RuntimeException("不能为空");
        }
        if (!(name.length() >= 2 && name.length() <= 4)) {
            throw new RuntimeException("用户名长度不符合要求");
        }
        //密码的长度为6，要求全是数字 isDigital
        boolean isNum = true;
        for (int i = 0; i < pwd.length(); i++) {
            if (!(Character.isDigit(pwd.charAt(i)))) {
                isNum = false;
                break;
            }
        }
        if (!(pwd.length() == 6 && isNum)) {
            throw new RuntimeException("密码不符合要求");
        }
        if (!((email.indexOf('@') >= 0) && (email.indexOf('.') > email.indexOf('@')))) {
            throw new RuntimeException("邮箱不符合要求");
        }
        System.out.println("注册成功");
    }
}
