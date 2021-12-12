package com.wxh.service.impl;

import com.wxh.entity.Book;
import com.wxh.entity.PurchaseRecord;
import com.wxh.repository.BookRepository;
import com.wxh.repository.BrowseRepository;
import com.wxh.repository.CartRepository;
import com.wxh.repository.impl.BookRepositoryImpl;
import com.wxh.repository.impl.BrowseRepositoryImpl;
import com.wxh.repository.impl.CartRepositoryImpl;
import com.wxh.repository.PaymentRepository;
import com.wxh.repository.impl.PaymentRepositoryImpl;
import com.wxh.service.BookService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {


    private BookRepository bookRepository=new BookRepositoryImpl();
    private CartRepository cartRepository=new CartRepositoryImpl();
    private PaymentRepository paymentRepository=new PaymentRepositoryImpl();
    private BrowseRepository browseHistoryRecord=new BrowseRepositoryImpl();
    //每一页展示多少本书
    private final Integer LIMIT=6;

    //根据page和index，调用仓库，从数据库中拿到书目
    @Override
    public List<Book> findAll(Integer page) {
        //index为索引，即得到当前页面的第一本书在数据库中是第几本
        int index=(page-1)*LIMIT;
        return bookRepository.findAll(index,LIMIT);//参数含义，从第index本书开始，查询往后的LIMIT（6）本书
    }

    //通过查询的书籍总数 和 每页的展示数量 得到总页数
    @Override
    public int getTotalPages() {
        Integer count=bookRepository.count();
        int page=0;
        page=(count+LIMIT-1)/LIMIT;
        return page;
    }

    @Override
    public void addCart(Integer bookId, String username) {
        cartRepository.insert(bookId,username);
    }

    @Override
    public List<Book> findByName(String name) {
        //调用函数按关键字查询书籍
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> findMyCart(String username) {

        return cartRepository.findMyCart(username);
    }


    @Override
    public boolean cartDelete(String username, int bookId) {
        return cartRepository.cartDelete(username,bookId);
    }

    @Override
    public Book findById(Integer bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public Float getTotalPrice(String username) {

        return cartRepository.getTotalPrice(username);
    }

    @Override
    public boolean bookDelete(Integer bookId) {
        return bookRepository.bookDelete(bookId);
    }

    @Override
    public boolean addBook(Book book) {
        return bookRepository.addBook(book);
    }

    @Override
    public boolean modifyBook(Book book) {

        return bookRepository.modifyBook(book);
    }

    @Override
    public boolean browseHistoryRecord(String username, Integer bookId, Date dateTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime_format=df.format(dateTime);

        return browseHistoryRecord.browseHistoryRecord(username,bookId,dateTime_format);
    }

    @Override
    public boolean paymentHistoryRecord(String username, Integer bookId, Date dateTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime_str=df.format(dateTime);
        return paymentRepository.paymentRecord(username,bookId,dateTime_str);
    }

    @Override
    public List<Book> getSaleList() {
        return paymentRepository.getSaleList();
    }

    @Override
    public List<PurchaseRecord> PurchaseRecord() {
        return paymentRepository.PurchaseRecord();
    }

    @Override
    public List<PurchaseRecord> BrowseRecord() {
        return browseHistoryRecord.BrowseRecord();
    }
}
