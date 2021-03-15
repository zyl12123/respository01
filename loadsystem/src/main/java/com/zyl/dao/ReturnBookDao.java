package com.zyl.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ReturnBookDao {
    public void returnBook(Connection conn, int id, String userName)throws SQLException;
}
