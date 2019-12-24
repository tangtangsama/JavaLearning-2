package cn.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author sucre
 * @date 2019-12-24
 * @time 09:56
 * @description JDBC进行事务管理，将多条sql整合到一个事务中，若中途产生异常，进行事务回滚
 */

public class JdbcDemo4 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            conn = JdbcUtils.getConnection();
            //执行sql前开启事务
            conn.setAutoCommit(false);

//          定义sql
//          张三-500，李四+500
            String sql1 = "update account set balance = balance - ? where id = ?";
            String sql2 = "update account set balance = balance + ? where id = ?";

            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);

            pstmt1.setInt(1,500);
            pstmt1.setInt(2,1);
            pstmt2.setInt(1,500);
            pstmt2.setInt(2,2);

            pstmt1.executeUpdate();
//          手动设置异常，若没有进行事务管理，此处异常抛出后数据库操作1依旧执行
            int i = 3/0;
            pstmt2.executeUpdate();
//          所有sql完成后提交事务，将所有sql操作包含到一个事务中
            conn.commit();
        } catch (Exception e) {
//          在catch中回滚事务，如果发生异常，回到sql执行前状态
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JdbcUtils.close(pstmt1,conn);
            JdbcUtils.close(pstmt2,null);
        }
    }
}
