package com.zyl.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.zyl.dao.UserDao;
import com.zyl.dao.UserDaoImpl;
import com.zyl.entity.UserInfo;

public class UserService {
    UserDao userDao = new UserDaoImpl();
    public  boolean checkUser(Connection conn,UserInfo userInfo) throws SQLException
    {
        Boolean status = false;
        try {
            conn.setAutoCommit(false);
            status = userDao.queryUser(conn, userInfo);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
        return status;
    }

}
