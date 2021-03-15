package com.zyl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnBookDaoImpl implements ReturnBookDao{
    public void returnBook(Connection conn, int id, String userName)throws SQLException {


        String saveSql = "UPDATE bookinfo SET state=?, borrowerid=? WHERE bookid=?";
        PreparedStatement ps=conn.prepareStatement(saveSql);

        ps.setInt(1, 0);
        ps.setInt(2, 0);
        ps.setInt(3, id);
        ps.execute();

    }
}
