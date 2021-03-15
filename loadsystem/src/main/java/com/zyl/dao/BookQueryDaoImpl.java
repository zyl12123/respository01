package com.zyl.dao;

import com.zyl.entity.BookInfo;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookQueryDaoImpl implements BookQueryDao {
    @Override
    public List<BookInfo> queryAll(Connection conn) throws SQLException {
        List<BookInfo> list=new ArrayList<>();
        String saveSql = "select * from bookinfo";
        PreparedStatement ps=conn.prepareStatement(saveSql);
        ResultSet resultSet=ps.executeQuery();
        while(resultSet.next())
        {
            BookInfo bookInfo=new BookInfo();
            bookInfo.setBookid(resultSet.getInt("bookid"));
            bookInfo.setBookname(resultSet.getString("bookname"));
            bookInfo.setAuthor(resultSet.getString("author"));
            bookInfo.setState(resultSet.getInt("state"));
            bookInfo.setBorrowerid(resultSet.getInt("borrowerid"));
            list.add(bookInfo);

        }
        return list;


    }
}
