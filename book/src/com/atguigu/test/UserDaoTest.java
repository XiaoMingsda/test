package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserByUsername("admin");
        if (user == null) {
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.saveUser(new User(null, "wzg168", "123456", "wzg168@qq.com")));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserByUsernameAndPassword("admin", "admin");
        if (user == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("登录成功");
        }
    }
}