package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/book/index.jsp");
    }

    /**
     * 处理登录请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User tmpUser = userService.login(new User(null, username, password, "asd"));
        if (tmpUser == null) {
            //System.out.println("用户名或密码错误");
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            System.out.println("登录成功");
            //把登录信息保存在Session域里
            req.getSession().setAttribute("user", tmpUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }

    /**
     * 处理注册的请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


        /* 可以把它写成工具类
        //用BeanUtils的populate方法注入JavaBean
        try {
            User user = new User();
            System.out.println("注入之前：" + user);
            BeanUtils.populate(user, req.getParameterMap());
            System.out.println("注入之后：" + user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        /*
            遍历键值对
            Map<String, String[]> parameterMap = req.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                System.out.println(entry.getKey() + " = " + Arrays.asList(entry.getValue()));
            }
        */

        /* 代码可以继续优化
        User user = new User();
        WebUtils.copyParamToBean(req.getParameterMap(), user);
         */
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + Arrays.asList(entry.getValue()));
        }

        //检查验证码是否正确 要求验证码为 abcde
        if (token != null && token.equalsIgnoreCase(code)) {
            if (userService.existsUsername(username)) {
                //System.out.println("用户名[" + username + "]已存在");
                req.setAttribute("msg", "用户名[" + username + "]已存在");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/user/regist.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                userService.registUser(new User(null, username, password, email));
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/user/regist_success.jsp");
                requestDispatcher.forward(req, resp);
                System.out.println("注册成功");
            }
        } else {
            //System.out.println("验证码[" + code + "]错误");
            req.setAttribute("msg", "验证码[" + code + "]错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/user/regist.jsp");
            requestDispatcher.forward(req, resp);
        }

    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

}
