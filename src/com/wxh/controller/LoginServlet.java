package com.wxh.controller;

import com.wxh.entity.Admin;
import com.wxh.entity.Book;
import com.wxh.entity.User;
import com.wxh.service.BookService;
import com.wxh.service.LoginService;
import com.wxh.service.impl.BookServiceImpl;
import com.wxh.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService =new LoginServiceImpl();
    /**
     * 处理登录的业务逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取账号密码
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        //用户名为空则重定向回登录界面
        if(username==""){
            resp.sendRedirect("login.jsp");
            return;
        }

        //判断登录类型 用户or管理员
        String type=req.getParameter("type");
        //调用下一层loginService的方法访问数据库
        Object object=loginService.login(username,password,type);

            if(object!=null){
                //如果能查到username及其对应的password的对象，登陆成功
            HttpSession session=req.getSession();
            switch(type){
                case "user":
                    User user=(User)object;
                    //将用户信息存入session
                    session.setAttribute("user",user);
                    //访问index.jsp，输出第一页的书籍
                    resp.sendRedirect("/book?page=1");
                    break;
                case "admin":
                    Admin admin=(Admin) object;
                    session.setAttribute("admin",admin);
                    //重定向到管理员界面
                    resp.sendRedirect("/adminPage.jsp");
                    break;
            }
        }else {
                //将错误信息存入request，转发回login
            req.setAttribute("loginState","fail");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
