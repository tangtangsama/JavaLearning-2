package cn.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author sucre
 * @date 2019-12-24
 * @time 12:11
 * @description 使用连接池抽取JDBC工具类
 * 定义工具类
 * 1. 定义一个类 JDBCUtils
 * 2. 提供静态代码块加载配置文件，初始化连接池对象
 * 3. 提供方法
 *      1. 获取连接方法：通过数据库连接池获取连接
 *      2. 释放资源
 *      3. 获取连接池的方法
 */

public class JdbcUtils {
    //    1.定义成员变量
    private static DataSource ds;

    static {
        try {
//            1.加载配置文件
            Properties pro = new Properties();
            InputStream is  = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            if (is != null) {
                pro.load(is);
            }
//            2.获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    2.获取连接
    public static Connection getConection() throws SQLException {
        return ds.getConnection();
    }

    //    3.释放资源
    public static void close(Statement stmt, Connection conn) {
        close(null, stmt, conn);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
//    4.获取连接池方法
    public static DataSource getDataSource(){
        return ds;
    }
}
