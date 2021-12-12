package com.wxh.controller;

import com.wxh.entity.Admin;
import com.wxh.entity.Book;
import com.wxh.entity.User;
import com.wxh.service.BookService;
import com.wxh.service.impl.BookServiceImpl;
import com.wxh.entity.PurchaseRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private BookService bookService=new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String method=req.getParameter("method");
        if(method==null){
            method="findDelete";
        }
        //流程控制
        HttpSession session=req.getSession();
        Admin admin=(Admin) session.getAttribute("admin");

        switch(method){
            case "findDelete":
                String pageStr=req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Book> list=bookService.findAll(page);
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getTotalPages());
                req.getRequestDispatcher("adminDelete.jsp").forward(req,resp);
                break;
            case "adminSalesSituation":
                List<Book> saleList=bookService.getSaleList();
                req.setAttribute("saleList",saleList);
                req.getRequestDispatcher("/adminSales.jsp").forward(req,resp);
                break;

            case "PurchaseRecord":
                List<PurchaseRecord> purchaseRecordList=bookService.PurchaseRecord();
                req.setAttribute("purchaseRecordList",purchaseRecordList);
                req.getRequestDispatcher("/adminUserPayment.jsp").forward(req,resp);
                break;

            case "BrowseRecord":
                List<PurchaseRecord> browseRecordList=bookService.BrowseRecord();
                req.setAttribute("browseRecordList",browseRecordList);
                req.getRequestDispatcher("/adminUserBrowse.jsp").forward(req,resp);
                break;
        }
    }

}
