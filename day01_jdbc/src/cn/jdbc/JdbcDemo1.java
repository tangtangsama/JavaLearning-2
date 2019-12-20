package cn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author sucre
 * @date 2019-12-19
 * @time 20:20
 * @description jdbc使用示例
 */

public class JdbcDemo1 {
    public static void main(String[] args){

        Connection conn = null;
        Statement stmt = null;
        try {
//        1.导入驱动jar包
//        2.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
//        3.获取数据库连接Connection对象
//        * url：指定连接的路径
//        * 语法：jdbc:mysql://ip地址(域名):端口号/数据库名称
//        * 如果连接的是本机mysql服务器，并且mysql服务默认端口是3306，则url可以简写为：jdbc:mysql:///数据库名称
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=UTC"
                    ,"root","123456");
//        4.定义sql语句
            String sql = "update salarygrade set losalary = 10000 where grade = 1";
//        5.获取执行sql的Statement对象
//        * boolean execute(String sql) ：可以执行任意的sql
//        * int executeUpdate(String sql) ：执行DML（insert、update、delete）语句、DDL(create，alter、drop)语句
//          返回值：影响的行数，可以通过这个影响的行数判断DML语句是否执行成功 返回值>0的则执行成功，反之，则失败。
//        * ResultSet executeQuery(String sql)  ：执行DQL（select)语句
            stmt = conn.createStatement();
//        6.执行sql
            int count = stmt.executeUpdate(sql);
//        7.处理结果
            System.out.println("修改的行数：" + count);
            if(count > 0){
                System.out.println("更新成功！");
            }else {
                System.out.println("更新失败！");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
//        8.释放资源
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
}
