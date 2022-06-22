package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println("订单号是：" + orderService.createOrder(cart, 1));
    }
}