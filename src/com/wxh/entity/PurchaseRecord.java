package com.wxh.entity;

import java.util.Date;

public class PurchaseRecord {
    public User user;
    public Book book;
    public Date date;

    public PurchaseRecord(User user, Book book, Date date) {
        this.user = user;
        this.book = book;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
