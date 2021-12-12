package com.wxh.service.impl;

import com.wxh.entity.User;
import com.wxh.repository.AdminRepository;
import com.wxh.repository.UserRepository;
import com.wxh.repository.impl.AdminRepositoryImpl;
import com.wxh.repository.impl.UserRepositoryImpl;
import com.wxh.service.LoginService;

public class LoginServiceImpl implements LoginService {

    //创建两个Repository的对象，用来调用函数
    private UserRepository userRepository=new UserRepositoryImpl();
    private AdminRepository adminRepository=new AdminRepositoryImpl();
    @Override
    public Object login(String username, String password,String type) {
        Object object=null;
        //通过type判断账户类型
        switch(type){
            case"user":
                //访问Dao层获取数据对象
                object=userRepository.login(username,password);
                break;
            case "admin":
                object=adminRepository.login(username,password);
                break;
        }

        return object;
    }
}
