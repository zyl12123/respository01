package com.zyl.service;

import com.zyl.dao.BookQueryDao;
import com.zyl.dao.BookQueryDaoImpl;
import com.zyl.entity.BookInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QueryService {
    BookQueryDao bookQueryDao=new BookQueryDaoImpl();
    public List<BookInfo> queryBook(Connection conn) throws SQLException {
        return bookQueryDao.queryAll(conn);
    }
}
