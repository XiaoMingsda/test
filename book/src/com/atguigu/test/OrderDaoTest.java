package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        //Order(String orderId, Date createTime, Double price, Integer status, Integer userId)
        orderDao.saveOrder(new Order("1234567890", new Date(), new Double(100), 0, 1));
    }
}