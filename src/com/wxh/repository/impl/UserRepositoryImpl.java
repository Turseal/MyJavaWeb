package com.wxh.repository.impl;

import com.wxh.entity.User;
import com.wxh.repository.UserRepository;
import com.wxh.utils.JDBCTools;



import java.sql.*;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User login(String username, String password) {
        //调用utils文件中的类函数获得connection
        Connection connection = JDBCTools.getconnection();
        //sql语句查询账户是否存在，密码是否正确
        String sql="select * from users where username = ? and password = ?";
        PreparedStatement statement=null;
        ResultSet resultSet = null;
        User user=null;
        try {
            //初始化statement
            statement=connection.prepareStatement(sql);
            //设置sql中的？参数
            statement.setString(1,username);
            statement.setString(2,password);
            //执行查询
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                //只要查到了，就说明密码正确，可以返回对象了
                user=new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //释放c3p0资源
            JDBCTools.release(connection,statement,resultSet);
        }
        return user;
    }

    //不会获得密码password，保证安全问题
    @Override
    public User GetUserByName(String username) {
        //获得连接
        Connection connection = JDBCTools.getconnection();
        //准备sql语句
        String sql="select * from users where username = ?";
        PreparedStatement statement=null;
        ResultSet resultSet = null;
        User user=null;
        try {
            //初始化statement
            statement=connection.prepareStatement(sql);
            //设置查询信息
            statement.setString(1,username);
            //执行查询
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                //返回password为空，防止密码泄露
                user=new User(resultSet.getString(1),"",resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return user;
    }

    @Override
    public boolean Registered(User user) {
        Connection connection = JDBCTools.getconnection();
        String sql="insert into users values(?,?,?)";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getEmail());
            //执行数据库更新语句
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,null);
        }
        return true;
    }
}
