package com.atguigu.dao;

import com.atguigu.pojo.User;

public interface UserDao {
    //根据用户名查询用户信息
    //如果返回null，说明没有这个用户
    public User queryUserByUsername(String username);

    //保存用户信息
    public int saveUser(User user);

    //根据用户名和密码查询用户
    //如果两个其中一个有错，则登录不成功
    public User queryUserByUsernameAndPassword(String username, String password);
}
