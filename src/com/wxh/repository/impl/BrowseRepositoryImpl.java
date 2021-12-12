package com.wxh.repository.impl;

import com.wxh.entity.Book;
import com.wxh.entity.PurchaseRecord;
import com.wxh.entity.User;
import com.wxh.repository.BrowseRepository;
import com.wxh.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrowseRepositoryImpl implements BrowseRepository {
    @Override
    public boolean browseHistoryRecord(String username, Integer bookId, String dateTime_str) {
        Connection connection= JDBCTools.getconnection();
        String sql="INSERT INTO browsehistory values (?,?,?)";
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
    public List<PurchaseRecord> BrowseRecord() {
        Connection connection= JDBCTools.getconnection();
        String sql="SELECT username,bookid,dateTime,name,price FROM browsehistory,book where book.id=browsehistory.bookId ORDER BY dateTime";
        PreparedStatement statement=null;
        List<PurchaseRecord> browseRecordList=new ArrayList<>();
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                browseRecordList.add(new PurchaseRecord(
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
        return browseRecordList;
    }
}
