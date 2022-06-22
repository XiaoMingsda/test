package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("加入购物车");

        //获取请求的参数 商品编号
        Integer bookId = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id):Book得到图书信息
        Book book = bookService.queryBookById(bookId);
        //把图书信息转换称为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(CartItem);添加商品项
        //Cart cart = new Cart();
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        //最后一个添加的商品名称
        //由于这里为请求重定向，所以用Session来存储回显的数据
        req.getSession().setAttribute("lastName", cartItem.getName());
        //重定向回商品列表页面
        //resp.sendRedirect(req.getContextPath());
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //删除了购物车商品项
            cart.deleteItem(id);
            //重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart != null) {
            //清空购物车
            cart.clear();
            //重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号，商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        //获取Cart购物车对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("加入购物车");

        //获取请求的参数 商品编号
        Integer bookId = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id):Book得到图书信息
        Book book = bookService.queryBookById(bookId);
        //把图书信息转换称为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(CartItem);添加商品项
        //Cart cart = new Cart();
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        //返回购物车总的商品数量和最后一个添加的商品名称
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

}
