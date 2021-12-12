package com.wxh.repository.impl;

import com.wxh.entity.Admin;
import com.wxh.entity.Book;
import com.wxh.entity.User;
import com.wxh.repository.AdminRepository;
import com.wxh.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public Admin login(String username, String password) {
        Connection connection = JDBCTools.getconnection();
        String sql="select * from admin where username = ? and password = ?";
        PreparedStatement statement=null;
        ResultSet resultSet = null;
        Admin admin=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                admin=new Admin(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return admin;
    }

}
