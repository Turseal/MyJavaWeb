package com.wxh.repository;

import com.wxh.entity.Book;
import com.wxh.entity.PurchaseRecord;

import java.util.Date;
import java.util.List;

public interface PaymentRepository {
    public boolean paymentRecord(String username, Integer bookId, String dateTime_str);
    public List<Book> getSaleList();
    public List<PurchaseRecord> PurchaseRecord();
}
