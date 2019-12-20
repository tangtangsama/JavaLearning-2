package cn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author sucre
 * @date 2019-12-20
 * @time 14:01
 * @description jdbc基本操作 插入记录
 */

public class JdbcDemo2 {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "insert into emp values(1015,'张飞',4,1007,null,14000.00,null,10)";
            conn = DriverManager.getConnection("jdbc:mysql:///db1?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8","root","123456");
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
            if(count > 0){
                System.out.println("添加成功！");
            }else{
                System.out.println("添加失败！");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
//            避免空指针异常
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
