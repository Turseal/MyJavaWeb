package com.wxh.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTools {
    private static DataSource dataSource;

    static{
        //获取新的c3p0数据池资源
        dataSource = new ComboPooledDataSource("c3p0");
    }
    public static Connection getconnection(){
        Connection connection=null;
        try {
            //获得链接
            connection=dataSource.getConnection();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //返回c3p0数据池连接
        return connection;
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet)
    {
        try {
            if(connection!=null) {
                connection.close();
            }
            if(statement!=null){
                statement.close();;
            }
            if(resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void main(String[] args) {
        System.out.println(JDBCTools.getconnection());
    }
}
