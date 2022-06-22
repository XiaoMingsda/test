package com.atguigu.service;

import com.atguigu.pojo.User;

//Service层表示业务层，一个业务一个方法
public interface UserService {
    /**
     * 注册用户
     * @param user 
     * @return void
     * @description 
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return null
     * @description
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     * @description
     */
    public boolean existsUsername(String username);
}
