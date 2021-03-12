package com.zyl.control;

import com.zyl.entity.UserInfo;
import com.zyl.service.UserService;
import com.zyl.util.JdbcUtil02;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ModifyPwdServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String oldPassWord = req.getParameter("oldPassWord");
        String newPassWord = req.getParameter("newPassWord");

        UserInfo user = new UserInfo();
        user.setUsername(userName);
        user.setPassword(oldPassWord);

        UserInfo user2 = new UserInfo();
        user2.setUsername(userName);
        user2.setPassword(newPassWord);

        Connection conn=null;
        try {
            conn= JdbcUtil02.getConnection();//获取数据库连接
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if(userService.checkUser(conn, user)){
                userService.modifyPasswordService(conn,user2);
                RequestDispatcher rd = req.getRequestDispatcher("/mod-success.html");
                rd.forward(req, resp);
            }else{
                RequestDispatcher rd = req.getRequestDispatcher("/mod-fail.html");

                rd.forward(req, resp);
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }
        //关闭数据库连接
        finally {
            JdbcUtil02.release(conn,userService.getUserDao().getPs(),userService.getUserDao().getRes());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
