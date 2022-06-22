package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        System.out.println(cart.toString());
        System.out.println("-----");
        System.out.println(cart.getTotalCount());
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        System.out.println(cart.toString());
        System.out.println("-----");
        cart.deleteItem(1);
        System.out.println(cart.toString());
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        System.out.println(cart.toString());
        System.out.println("-----");
        cart.clear();
        System.out.println(cart.toString());
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        cart.addItem(new CartItem(1, "时间简史", new Integer(1), new Double(100.12), new Double(100.12)));
        System.out.println(cart.toString());
        System.out.println("-----");
        cart.updateCount(1, 5);
        System.out.println(cart.toString());
    }
}