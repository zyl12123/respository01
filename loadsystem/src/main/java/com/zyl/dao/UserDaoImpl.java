package com.zyl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zyl.entity.UserInfo;

public class UserDaoImpl implements UserDao {
    public PreparedStatement getPs() {
        return ps;
    }
    public ResultSet getRes() {
        return res;
    }
    public PreparedStatement ps = null;
    public ResultSet res=null;


    @Override
    public void register(Connection conn, UserInfo userInfo) throws SQLException {
        String saveSql = "insert into userinfo(username,password,email) values(?,?,?)";
        ps=conn.prepareStatement(saveSql);
        ps.setString(1, userInfo.getUsername());
        ps.setString(2, userInfo.getPassword());
        ps.setString(3, userInfo.getEmail());
        ps.execute();

    }

    @Override
    public void modifyPassword(Connection conn, UserInfo userInfo) throws SQLException {
        String updateSql = "update userinfo set password=? where username=?";
        PreparedStatement ps = conn.prepareStatement(updateSql);
        ps.setString(1, userInfo.getPassword());
        ps.setString(2, userInfo.getUsername());

        ps.execute();
    }



    public  boolean queryUser(Connection conn,UserInfo userInfo) throws SQLException{
        String sql = "select * from userinfo where username=? and password=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, userInfo.getUsername());
        ps.setString(2, userInfo.getPassword());
        ResultSet rst = ps.executeQuery();
        if(rst.next())
            return true;
        else {
            return false;
        }
    }
}