package com.wxh.repository;

import com.wxh.entity.Admin;
import com.wxh.entity.Book;

import java.util.List;

public interface AdminRepository {
    public Admin login(String username,String password);

}
