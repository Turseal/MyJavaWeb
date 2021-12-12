package com.wxh.repository.impl;

import com.wxh.entity.Book;
import com.wxh.repository.CartRepository;
import com.wxh.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartRepositoryImpl implements CartRepository {
    @Override
    public void insert(Integer bookId, String username) {
        Connection connection= JDBCTools.getconnection();
        String sql="insert into Cart values(?,?)";
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setInt(2,bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }

    @Override
    public List<Book> findMyCart(String username) {
        Connection connection= JDBCTools.getconnection();
        String sql="select id,name,price from cart,book where username= ? and cart.bookId=book.id";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Book> bookList=new ArrayList<>();
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                Book book=new Book(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getFloat("price"));
                bookList.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return bookList;
    }


    @Override
    public boolean cartDelete(String username,int bookId) {
        Connection connection= JDBCTools.getconnection();
        String sql="delete from cart where username=? and bookId=?";
        PreparedStatement statement=null;
        List<Book> bookList=new ArrayList<>();
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setInt(2,bookId);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,null);
        }
        return true;
    }

    @Override
    public Float getTotalPrice(String username) {
        Connection connection= JDBCTools.getconnection();
        String sql="select SUM(price) from cart,book where username= ? and cart.bookId=book.id";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Float ans=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                ans=resultSet.getFloat(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return ans;
    }
}
