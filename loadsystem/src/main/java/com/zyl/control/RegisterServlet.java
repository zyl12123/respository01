package com.zyl.control;

import com.zyl.entity.UserInfo;
import com.zyl.service.UserService;
import com.zyl.util.JdbcUtil02;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord01");
        String passWord02 = req.getParameter("passWord02");
        String email = req.getParameter("email");



        if(passWord.equals(passWord02)&&passWord!=null&&passWord02!=null) {
            UserInfo user = new UserInfo();
            user.setUsername(userName);
            user.setPassword(passWord);
            user.setEmail(email);

            Connection conn = null;
            try {
                conn = JdbcUtil02.getConnection();//获取数据库连接
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            try {
                userService.registerService(conn, user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                JdbcUtil02.release(conn,userService.getUserDao().getPs(),userService.getUserDao().getRes());
            }
            //请求转发前面要加/
            req.getRequestDispatcher("/login.html").forward(req,resp);
        }
            else {
                req.getRequestDispatcher("/register.html").forward(req,resp); //两次密码或者为空不一样就重来

            }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
