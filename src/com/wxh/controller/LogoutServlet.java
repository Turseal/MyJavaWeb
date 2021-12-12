package com.wxh.controller;

import com.wxh.entity.Admin;
import com.wxh.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到session，并从中读取"user",以及“admin”
        HttpSession session= req.getSession();
        User user=(User) session.getAttribute("user");
        Admin admin=(Admin) session.getAttribute("admin");
        if(user!=null)
        {
            //如果存在user信息，将其从session中移除
            session.removeAttribute("user");
        }
        if(admin!=null)
        {
            //如果是admin，同理
            session.removeAttribute("admin");
        }
        //重定向到登录界面
        resp.sendRedirect("login.jsp");
    }
}
