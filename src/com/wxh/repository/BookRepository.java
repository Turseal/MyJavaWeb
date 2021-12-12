package com.wxh.repository;

import com.wxh.entity.Book;


import java.sql.Date;
import java.util.List;

public interface BookRepository {
    public List<Book> findAll(int index,int limit);
    public int count();
    public List<Book> findByName(String name);
    public Book findById(Integer bookId);
    public boolean bookDelete(Integer bookId);
    public boolean addBook(Book book);
    public boolean modifyBook(Book book);
}
