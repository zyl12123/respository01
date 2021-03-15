package com.zyl.service;

import com.zyl.dao.BookBorrowDao;
import com.zyl.dao.BookBorrowDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class BorrowService {
    BookBorrowDao bookBorrowDao=new BookBorrowDaoImpl();
    public void borrowService(Connection conn,int id,String userName) throws SQLException {
        bookBorrowDao.borrowBook(conn,id,userName);
    }
}
