package com.atguigu.web;

import com.atguigu.dao.UserDao;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取UserId
        User loginUser = (User)req.getSession().getAttribute("user");
        //确保用户已经登录
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = loginUser.getId();
        //生成订单，获得订单号
        String orderId = orderService.createOrder(cart, userId);

        //req.setAttribute("orderId", orderId);
        //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);

        //由于请求转发可能导致用户的订单重复提交，所以用请求重定向跳转页面
        //由于请求重定向不支持某些域对象，所以用Session来保存订单号
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");

    }
}
