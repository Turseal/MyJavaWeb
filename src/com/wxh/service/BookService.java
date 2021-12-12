package com.wxh.service;

import com.wxh.entity.Book;
import com.wxh.entity.PurchaseRecord;

import java.util.Date;
import java.util.List;

public interface BookService {

    public List<Book> findAll(Integer page);
    public int getTotalPages();
    public void addCart(Integer bookId,String username);
    public List<Book> findByName(String name);
    public Book findById(Integer bookId);
    public List<Book> findMyCart(String username);
    public boolean cartDelete(String username,int bookId);
    public Float getTotalPrice(String username);
    public boolean bookDelete(Integer bookId);
    public boolean addBook(Book book);
    public boolean modifyBook(Book book);
    public boolean browseHistoryRecord(String username, Integer bookId, Date dateTime);
    public boolean paymentHistoryRecord(String username, Integer bookId, Date dateTime);
    public List<Book> getSaleList();
    public List<PurchaseRecord> PurchaseRecord();
    public List<PurchaseRecord> BrowseRecord();
}
