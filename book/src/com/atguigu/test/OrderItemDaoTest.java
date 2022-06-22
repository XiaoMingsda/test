package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        //OrderItem(Integer id, String name, Integer count, Double price, Double totalPrice, String orderId)
        orderItemDao.saveOrderItem(new OrderItem(null, "java从入门到精通", 1, new Double(100), new Double(100), "1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null, "javaScript从入门到精通", 2, new Double(100), new Double(100), "1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null, "javaWeb从入门到精通", 3, new Double(100), new Double(100), "1234567890"));
    }
}