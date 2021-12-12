package com.wxh.filter;

import com.wxh.entity.Admin;
import com.wxh.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CheckLoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //检测用户是否完成登录
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");
        Admin admin=(Admin) session.getAttribute("admin");

        if(user!=null||admin!=null) {
            chain.doFilter(request, response);
        }
        else{
            response.sendRedirect("login.jsp");
        }
    }
}
