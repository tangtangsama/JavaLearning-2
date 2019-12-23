package cn.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * @author sucre
 * @date 2019-12-20
 * @time 15:06
 * @description 抽取JDBC工具类
 *              1. 注册驱动也抽取
 *              2. 抽取一个方法获取连接对象
 *              * 需求：不想传递参数（麻烦），还得保证工具类的通用性。
 *              * 解决：配置文件
 *              jdbc.properties
 *                 url=
 *                 user=
 *                 password=
 *              3. 抽取一个方法释放资源
 */

public class JdbcUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    /*
      文件的读取只需要一次即可，使用静态代码块完成
     */
    static {
        try {
//        1.创建集合类
            Properties pro = new Properties();
            //获取src路径下的文件的方式--->使用ClassLoader 类加载器
            ClassLoader classLoader = JdbcUtils.class.getClassLoader();
            URL res  = classLoader.getResource("jdbc.properties");
            String path = null;
            if (res != null) {
                path = res.getPath();
                System.out.println(path);
//              2. 加载文件
                pro.load(new FileReader(path));
            }
//        3.获取数据，赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
//        4.注册驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return 连接对象
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    /**
     * 释放资源 重载两个释放资源的方法
     * @param stmt
     * @param conn
     * @param rs
     */
    public static void close(Statement stmt, Connection conn, ResultSet rs){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement stmt, Connection conn){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
