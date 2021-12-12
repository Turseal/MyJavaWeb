package com.wxh.controller;

import com.wxh.entity.Admin;
import com.wxh.entity.Book;
import com.wxh.entity.User;
import com.wxh.service.BookService;
import com.wxh.service.impl.BookServiceImpl;
import com.wxh.utils.SendJMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookService bookService=new BookServiceImpl();

    /**
     * 集中加载图书数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if(method==null){
            //method为空则默认为首页的商品信息展示功能
            method="findAll";
        }
        //流程控制，得到session和user，在后续的功能中使用
        HttpSession session=req.getSession();
        User user=(User) session.getAttribute("user");


        switch(method){
            //找到对应page的书籍
            case "findAll":
                //要展示第几页
                String pageStr=req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                //调用service函数，找到该页的所有书籍信息List<Book>
                List<Book>list=bookService.findAll(page);
                //将书籍list，以及分页参数存入request，转发给主页面index
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getTotalPages());
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                break;

            //加入购物车
            case "addCart":
                Integer bookId=Integer.parseInt(req.getParameter("bookid"));
                //调用BookService方法将购物信息传入数据库
                bookService.addCart(bookId,user.getUsername());
                resp.sendRedirect("/book?method=findMyCart");
                break;
            //打开购物车
            case "findMyCart":
                //调用service函数查询购物车信息，存入request中
                List<Book>CartList=bookService.findMyCart(user.getUsername()) ;
                req.setAttribute("cartlist",CartList);
                //转发至cart.jsp
                req.getRequestDispatcher("cart.jsp").forward(req,resp);
                break;
            case "cartDelete":
                bookId=Integer.parseInt(req.getParameter("bookId"));
                bookService.cartDelete(user.getUsername(),bookId);
                req.getRequestDispatcher("/book?method=findMyCart").forward(req,resp);
                break;
            case "bookInfo":
                //得到书籍编号，和当前主页page（为了回到首页时能回到当前页面）
                bookId=Integer.parseInt(req.getParameter("bookid"));
                Integer currentPage=Integer.parseInt(req.getParameter("currentPage"));
                //调用service函数查询得到书籍详情信息
                Book book = bookService.findById(bookId);
                //记录用户浏览记录
                bookService.browseHistoryRecord(user.getUsername(),bookId,new Date());
                //将书籍信息book，和原主页页面page存入req，bookInformation.jsp
                req.setAttribute("book",book);
                req.setAttribute("currentPage",currentPage);
                req.getRequestDispatcher("bookInformation.jsp").forward(req,resp);
                break;
            case "payment":
                //得到用户名，以此为参数，多表查询，得到金额总和
                Float totalPrice=bookService.getTotalPrice(user.getUsername());
                req.setAttribute("totalPrice",totalPrice);
                req.getRequestDispatcher("payment.jsp").forward(req,resp);
                break;

            case "bookDelete":
                bookId=Integer.parseInt(req.getParameter("bookId"));
                bookService.bookDelete(bookId);
                req.getRequestDispatcher("/admin?method=findDelete&page=1").forward(req,resp);
                break;
            case "success":
                //找到用户购物车信息，用来清空购物车，以及发送邮件
                CartList=bookService.findMyCart(user.getUsername());
                List<String> bookNameList=new ArrayList<>();
                for (Book book1 : CartList) {
                    //将书名存入list
                    bookNameList.add(book1.getName());
                    //记录用户购买信息
                    bookService.paymentHistoryRecord(user.getUsername(),book1.getId(),new Date());
                    //清除购物车
                    bookService.cartDelete(user.getUsername(),book1.getId());
                }
                try {
                    //调用函数发送邮件
                    SendJMail.sendMail(bookNameList,user.getEmail());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //回到购物车页面
                req.getRequestDispatcher("/book?method=findMyCart").forward(req,resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        int i=0;
        switch(method) {
            case "search":
                String name = req.getParameter("search_name");
                //调用Service层函数，查询对应书名的书籍信息List
                List<Book> bookList = bookService.findByName(name);
                if (bookList != null) {
                    //将List存入request中，回到主页时就能展示出对应关键字的书籍了
                    req.setAttribute("list", bookList);
                }
                //设置当前页面
                req.setAttribute("dataPrePage", 6);
                req.setAttribute("currentPage", 1);
                req.setAttribute("pages", bookService.getTotalPages());
                //是查询，将查询的关键字返回，使得搜索框里保留原关键字
                req.setAttribute("searchWord",name);
                //转发回主页
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;

            case "addBook":
                Integer bookId=-1;//抹除，不再使用
                String bookName=req.getParameter("bookName");
                Float price=Float.parseFloat(req.getParameter("price"));
                Book book=new Book(bookId,bookName,price);
                bookService.addBook(book);
                break;

            case "modifyBook":
                bookId=Integer.parseInt(req.getParameter("bookId"));
                bookName=req.getParameter("bookName");
                price=Float.parseFloat(req.getParameter("price"));
                book=new Book(bookId,bookName,price);
                bookService.modifyBook(book);
                resp.sendRedirect("/admin?method=findDelete&page=1");
                break;
        }
    }
}
