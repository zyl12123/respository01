package com.zyl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookBorrowDaoImpl implements BookBorrowDao{
    public void borrowBook(Connection conn,int id,String userName)throws SQLException {
        String sql="select id from userinfo where username=?";//编写sql
        PreparedStatement ps2=conn.prepareStatement(sql);

        ps2=conn.prepareStatement(sql);//预编译sql，先写sql，然后不执行
        ps2.setString(1,userName);//传递参数
        ResultSet resultSet=ps2.executeQuery();
        int borrowerId=-1;
        while(resultSet.next())
        {
             borrowerId=resultSet.getInt("id");
        }
        System.out.println(borrowerId);

        String saveSql = "UPDATE bookinfo SET state=?, borrowerid=? WHERE bookid=?";
        PreparedStatement ps=conn.prepareStatement(saveSql);

        ps.setInt(1, 1);
        ps.setInt(2, borrowerId);
        ps.setInt(3, id);
        ps.execute();

    }
}
