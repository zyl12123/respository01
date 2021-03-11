package com.zyl.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zyl.entity.UserAddr;
import com.zyl.entity.UserInfo;

public interface UserDao {
    public void save(Connection conn,UserAddr userAddr) throws SQLException;
    public void update(Connection conn,UserAddr userAddr) throws SQLException;
    public void delete(Connection conn,long id) throws SQLException;
    public UserAddr singalSelect(Connection conn, long id) throws SQLException;
    public boolean queryUser(Connection conn,UserInfo userInfo) throws SQLException;
}
