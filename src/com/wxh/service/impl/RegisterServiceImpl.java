package com.wxh.service.impl;

import com.wxh.entity.User;
import com.wxh.repository.UserRepository;
import com.wxh.repository.impl.UserRepositoryImpl;
import com.wxh.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {

    private UserRepository userRepository=new UserRepositoryImpl();

    //使用username查询用户信息，查询的密码password设置为“”，防止密码泄露
    @Override
    public User GetUserByName(String username) {
        //调用Repository获得用户信息
        return userRepository.GetUserByName(username);
    }

    @Override
    public boolean registered(String username, String password, String email) {
        //将调用dao层函数，将信息存入数据库
        return userRepository.Registered(new User(username,password,email));
    }
}
