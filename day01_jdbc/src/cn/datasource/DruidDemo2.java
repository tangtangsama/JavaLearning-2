package cn.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author sucre
 * @date 2019-12-24
 * @time 12:26
 * @description 使用连接池的数据库工具类
 */
public class DruidDemo2 {
    public static void main(String[] args) {
        /*
        完成添加操作，给account表添加一条记录
         */
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
//            1.获取连接
            conn= JdbcUtils.getConection();
//            2.定义sql
            String sql= "insert into account values(?,?,?)";
            pstmt = conn.prepareStatement(sql);
//            3.sql赋值
            pstmt.setInt(1,3);
            pstmt.setString(2,"wangwu");
            pstmt.setInt(3,1000);
//            4.执行sql
            int count = pstmt.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
//            5.关闭连接池
            JdbcUtils.close(pstmt,conn);
        }
    }
}
