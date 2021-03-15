package com.zyl.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zyl.service.UserService;
import com.zyl.entity.UserInfo;
import com.zyl.util.JdbcUtil02;
/*
* 登录
* */
public class UserServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        UserInfo user = new UserInfo();
        user.setUsername(userName);
        user.setPassword(passWord);

        //ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        //Connection conn = connectionFactory.getConnection();
        Connection conn=null;
        try {
            conn= JdbcUtil02.getConnection();//获取数据库连接
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(userService.checkUser(conn, user)){
                System.out.println("userServlet.doPost()"+"登陆成功");

                HttpSession session=req.getSession();

                session.setAttribute("userName",userName);

                //请求转发，url不变，要加/，代表web应用
                RequestDispatcher rd = req.getRequestDispatcher("/home.html");
                req.setAttribute("name","hhh");

                rd.forward(req, resp);
            }else{
                System.out.println("userServlet.doPost()"+"登陆失败");
                RequestDispatcher rd = req.getRequestDispatcher("/login-error.html");

                rd.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtil02.release(conn,userService.getUserDao().getPs(),userService.getUserDao().getRes());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}