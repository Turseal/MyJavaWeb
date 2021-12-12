package com.wxh.service;

import com.wxh.entity.User;

public interface RegisterService {
    public User GetUserByName(String username);
    public boolean registered(String username,String password,String email);
}
