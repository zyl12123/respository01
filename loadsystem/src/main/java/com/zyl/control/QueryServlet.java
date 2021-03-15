package com.zyl.control;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import com.zyl.dao.BookQueryDao;
import com.zyl.dao.BookQueryDaoImpl;
import com.zyl.entity.BookInfo;
import com.zyl.service.QueryService;
import com.zyl.util.JdbcUtil02;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.hutool.json.JSONObject.*;
import static net.sf.json.JSONObject.fromObject;


public class QueryServlet extends HttpServlet {
    QueryService queryService=new QueryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=null;
        try {
            conn= JdbcUtil02.getConnection();//获取数据库连接
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<BookInfo> list=new ArrayList<>();
        try {
            list= queryService.queryBook(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



            try {
                //下面这个包太难导了，我傻了。

                JSONObject data = new JSONObject();
/*
                for(int i=0;i< list.size();i++) {

                    data.put("bookid"+i, list.get(0).getBookid());
                    data.put("bookname"+i, list.get(0).getBookname());
                    data.put("author"+i, list.get(0).getAuthor());
                    if (list.get(0).getState() == 0) {
                        data.put("state"+i, "未借出");
                    } else {
                        data.put("state"+i, "已借出");
                    }
                }
 */
                data.put("data",list);
                //接下来发送数据
                /*设置编码，防止出现乱码问题*/
                resp.setCharacterEncoding("utf-8");
                /*得到输出流*/
                PrintWriter respWritter = resp.getWriter();
                /*将JSON格式的对象toString()后发送*/
                respWritter.append(data.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
