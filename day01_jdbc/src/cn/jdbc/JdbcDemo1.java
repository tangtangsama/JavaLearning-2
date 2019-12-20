package cn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author sucre
 * @date 2019-12-19
 * @time 20:20
 * @description
 */

public class JdbcDemo1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        1.导入驱动jar包
//        2.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
//        3.获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=UTC"
                ,"root","123456");
//        4.定义sql语句
        String sql = "update salarygrade set losalary = 10000 where grade = 1";
//        5.获取执行sql的Statement对象
        Statement stmt = conn.createStatement();
//        6.执行sql
        int count = stmt.executeUpdate(sql);
//        7.处理结果
        System.out.println("修改的行数：" + count);
//        8.释放资源
        stmt.close();
        conn.close();
    }
}
