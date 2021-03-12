package com.zyl.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.*;

public class JdbcUtil02 {
    private static String driver=null;
    private static String url=null;
    private static String user=null;
    private static String password=null;

    static
    {
        try{
            //jdbd的包导入jid/jre/exrt里面,还有就是配置文件的寻址.
            //InputStream in=JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
            InputStream in = new FileInputStream("D:\\iedownload\\loadsystem\\src\\main\\java\\com\\zyl\\db.properties");
            Properties properties=new Properties();
            properties.load(in);
            driver=properties.getProperty("driver");
            System.out.println(driver);
            //url=properties.getProperty("url");这里是为了处理乱码问题修改的url。
            url="jdbc:mysql://localhost:3306/loadsystem?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
            System.out.println(url);
            user=properties.getProperty("user");
            password=properties.getProperty("password");
            //1. 驱动只要加载一次

        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("error");
        }

    }
    //获取连接
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url,user,password);
    }

    //释放连接资源
    public static void release(Connection conn,Statement state,ResultSet res)
    {
        if(res!=null)
            try {
                res.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        if(state!=null)
            try {
                state.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        if(conn!=null)
            try {
                conn.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }



}
