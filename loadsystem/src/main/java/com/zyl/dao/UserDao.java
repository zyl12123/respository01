package com.zyl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zyl.entity.UserInfo;

public interface UserDao {
    public PreparedStatement getPs();
    public ResultSet getRes();
    public void register(Connection conn, UserInfo userInfo) throws SQLException;//注册账户
    public void modifyPassword(Connection conn, UserInfo userInfo) throws SQLException;
    public boolean queryUser(Connection conn,UserInfo userInfo) throws SQLException;//查询账号密码是否正确
}
