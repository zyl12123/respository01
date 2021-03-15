package com.zyl.service;

import com.zyl.dao.BookBorrowDao;
import com.zyl.dao.BookBorrowDaoImpl;
import com.zyl.dao.ReturnBookDao;
import com.zyl.dao.ReturnBookDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class ReturnService {
    ReturnBookDao returnBookDao=new ReturnBookDaoImpl();
    public void returnService(Connection conn, int id, String userName) throws SQLException {
        returnBookDao.returnBook(conn,id,userName);
    }
}
