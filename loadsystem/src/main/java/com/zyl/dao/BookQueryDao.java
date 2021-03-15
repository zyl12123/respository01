package com.zyl.dao;

import com.zyl.entity.BookInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface BookQueryDao {
    public List<BookInfo> queryAll(Connection conn)throws SQLException;;
}
