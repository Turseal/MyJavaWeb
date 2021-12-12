package com.wxh.repository;

import com.wxh.entity.User;

public interface UserRepository {
    public User login(String username,String password);
    public User GetUserByName(String username);
    public boolean Registered(User user);
}
