package com.wxh.repository.impl;

import com.wxh.entity.Book;
import com.wxh.entity.User;
import com.wxh.repository.BookRepository;
import com.wxh.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    //找到index开始总共limit本的书籍
    @Override
    public List<Book> findAll(int index,int limit) {
        Connection connection= JDBCTools.getconnection();
        String sql="select * from book limit ?,?";//得到从？开始的总计？本书
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Book> bookList=new ArrayList<>();
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,index);
            statement.setInt(2,limit);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                //相当于用一个指针循环遍历resultSet，使用getInt等函数分别以select的列名，或顺序得到相应的数据
                //封装成Book存入bookList中，后续返回
                Book book=new Book(resultSet.getInt("id"),
                        resultSet.getString(2),resultSet.getFloat(3));
                bookList.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return bookList;
    }

    //统计数据库中书籍数量的总数
    @Override
    public int count() {
        Connection connection= JDBCTools.getconnection();
        String sql="select count(*) from book ";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int count=0;
        try {
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                count=resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return count;
    }

    @Override
    public List<Book> findByName(String name) {
        //获取connection
        Connection connection= JDBCTools.getconnection();
        String sql="select * from book where name like ?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Book> bookList=new ArrayList<>();
        try {
            statement=connection.prepareStatement(sql);
            //使用模糊查询
            statement.setString(1,'%'+name+'%');
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                //相当于用指针遍历resultSet，将查询结果全部放入BookList中，最终返回
                Book book=new Book(resultSet.getInt("id"),resultSet.getString(2),
                        resultSet.getFloat(3));
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
    public Book findById(Integer bookId) {
        Connection connection= JDBCTools.getconnection();
        String sql="select * from book where id=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Book book=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,bookId);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                book=new Book(resultSet.getInt("id"),resultSet.getString(2),resultSet.getFloat(3));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return book;
    }

    @Override
    public boolean bookDelete(Integer bookId) {
        Connection connection= JDBCTools.getconnection();
        String sql="DELETE  from book where id=?";
        PreparedStatement statement=null;
        Book book=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,bookId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,null);
        }
        return true;
    }

    @Override
    public boolean addBook(Book book) {
        Connection connection= JDBCTools.getconnection();
        //由于book的主码id为自增主码，因此不需要特殊指定
        String sql="Insert into book(name,price) values(?,?)";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,book.getName());
            statement.setFloat(2,book.getPrice());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,null);
        }
        return true;
    }

    @Override
    public boolean modifyBook(Book book) {
        Connection connection= JDBCTools.getconnection();
        //更新数据库的sql
        String sql="UPDATE book SET name=?,price=? WHERE id=?";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,book.getName());
            statement.setFloat(2,book.getPrice());
            statement.setInt(3,book.getId());
            //执行更新信息
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,null);
        }
        return true;
    }

}
