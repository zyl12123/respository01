package com.zyl.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyl.service.UserService;
import com.zyl.entity.UserInfo;
import com.zyl.util.JdbcUtil;

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
            conn= JdbcUtil.getConnection();//获取数据库连接
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(userService.checkUser(conn, user)){
                System.out.println("userServlet.doPost()"+"登陆成功");
                RequestDispatcher rd = req.getRequestDispatcher("welcome.jsp");
                req.setAttribute("userName", userName);
                req.setAttribute("passWord", passWord);
                req.setAttribute("login", "1");
                rd.forward(req, resp);
            }else{
                System.out.println("userServlet.doPost()"+"登陆失败");
                RequestDispatcher rd = req.getRequestDispatcher("welcome.jsp");
                req.setAttribute("userName", userName);
                req.setAttribute("passWord", passWord);
                rd.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}