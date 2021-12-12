package com.wxh.repository.impl;

import com.wxh.entity.Book;
import com.wxh.entity.PurchaseRecord;
import com.wxh.entity.User;
import com.wxh.repository.PaymentRepository;
import com.wxh.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {

    @Override
    public boolean paymentRecord(String username, Integer bookId, String dateTime_str) {
        Connection connection= JDBCTools.getconnection();
        String sql="INSERT INTO paymenthistory values (?,?,?)";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setInt(2,bookId);
            statement.setString(3,dateTime_str);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,null);
        }
        return true;
    }
    @Override
    public List<Book> getSaleList() {
        Connection connection= JDBCTools.getconnection();
        String sql="SELECT COUNT(id) as count,id,book.name,price FROM paymenthistory,book where book.id=paymenthistory.bookId GROUP BY id ORDER BY count DESC";
        PreparedStatement statement=null;
        List<Book> saleList=new ArrayList<>();
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                saleList.add(new Book(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getInt("count")
                        ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,null);
        }
        return saleList;
    }

    @Override
    public List<PurchaseRecord> PurchaseRecord() {
        Connection connection= JDBCTools.getconnection();
        String sql="SELECT username,bookid,dateTime,name,price FROM paymenthistory,book where book.id=paymenthistory.bookId ORDER BY dateTime";
        PreparedStatement statement=null;
        List<PurchaseRecord> purchaseRecordList=new ArrayList<>();
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                purchaseRecordList.add(new PurchaseRecord(
                        new User(resultSet.getString("username"),"",""),
                        new Book(resultSet.getInt("bookid"),resultSet.getString("name"),resultSet.getFloat("price")),
                        resultSet.getTimestamp("dateTime")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,null);
        }
        return purchaseRecordList;
    }
}
