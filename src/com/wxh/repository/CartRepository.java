package com.wxh.repository;

import com.wxh.entity.Book;

import java.util.List;

public interface CartRepository {
    public void insert(Integer bookId,String readerid);
    public List<Book> findMyCart(String username);
    public boolean cartDelete(String username,int bookId);
    public Float getTotalPrice(String username);

}
