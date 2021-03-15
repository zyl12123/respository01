package com.zyl.control;

import cn.hutool.json.JSONObject;
import com.zyl.service.ReturnService;
import com.zyl.util.JdbcUtil02;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class ReturnServlet extends HttpServlet {
    ReturnService returnService=new ReturnService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();

        String name=(String)session.getAttribute("userName");
//        resp.getWriter().write(name);

        String bookId = req.getParameter("bookid");
        int bookId1=Integer.valueOf(bookId);


        Connection conn=null;
        try {
            conn= JdbcUtil02.getConnection();//获取数据库连接
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            returnService.returnService(conn,bookId1,name);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        RequestDispatcher rd=req.getRequestDispatcher("/home.html");
        rd.forward(req,resp);




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
