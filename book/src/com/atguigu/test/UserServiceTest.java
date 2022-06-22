package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null, "bbj168", "666666", "bbj168@qq.com"));
    }

    @Test
    public void login() {
        User login = userService.login(new User(null, "wzg168", "123456", null));
        System.out.println(login);
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("wzg1688")) {
            System.out.println("用户名已存在！");
        } else {
            System.out.println("用户名可用!");
        }
    }
}