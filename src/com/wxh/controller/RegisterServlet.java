package com.wxh.controller;


import com.wxh.service.RegisterService;

import com.wxh.service.impl.RegisterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    //Service层对象，用来调用函数
    private RegisterService registerService=new RegisterServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //用户在前端页面输入的信息
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String re_password=req.getParameter("re_password");
        String email=req.getParameter("email");

        //此处用ajax实现的注册功能，当查询注册的用户名数据库中已经存在时
        if(registerService.GetUserByName(username)!=null) {
            //返回错误信息1
            resp.getWriter().write("1");
            return;
        }

        //判断密码是否一致
        if(!password.equals(re_password)){
            //如果密码不一致，返回错误信息2
            resp.getWriter().write("2");
            return;
        }
        //调用service方法将用户信息向下传递，最终存入数据库
        registerService.registered(username,password,email);
        //返回成功信息
        resp.getWriter().write("success");
        return;
//        resp.sendRedirect("login.jsp");
    }
}
