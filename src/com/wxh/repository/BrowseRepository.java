package com.wxh.repository;

import com.wxh.entity.PurchaseRecord;

import java.util.List;

public interface BrowseRepository {
    public boolean browseHistoryRecord(String username, Integer bookId, String dateTime_str);
    public List<PurchaseRecord> BrowseRecord();
}
