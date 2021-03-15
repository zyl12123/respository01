package com.zyl.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface BookBorrowDao {
    public void borrowBook(Connection conn,int id,String userName)throws SQLException;
}
