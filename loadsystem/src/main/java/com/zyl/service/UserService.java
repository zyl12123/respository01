package com.zyl.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.zyl.dao.UserDao;
import com.zyl.dao.UserDaoImpl;
import com.zyl.entity.UserInfo;
import com.zyl.util.JdbcUtil02;

public class UserService {
    UserDao userDao = new UserDaoImpl();
    public UserDao getUserDao(){return userDao;}
    public  boolean checkUser(Connection conn,UserInfo userInfo) throws SQLException
    {
        Boolean status = false;
        try {
            conn.setAutoCommit(false);   //关闭事务自动提交
            status = userDao.queryUser(conn, userInfo);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();   //出现异常就回滚

        }
        finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e3) {
                e3.printStackTrace();
            }

        }

        return status;
    }
    public void registerService(Connection conn,UserInfo userInfo) throws SQLException
    {
        try {
            conn.setAutoCommit(false);   //关闭事务自动提交
            userDao.register(conn,userInfo);
            conn.commit();

            } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();   //出现异常就回滚
        }
            finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e3) {
                    e3.printStackTrace();
                }

            }

    }

    public void modifyPasswordService(Connection conn,UserInfo userInfo) throws SQLException
    {
        try {
            conn.setAutoCommit(false);   //关闭事务自动提交
            userDao.modifyPassword(conn,userInfo);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();   //出现异常就回滚

        }
        finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e3) {
                e3.printStackTrace();
            }

        }

    }


}
