package cn.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author sucre
 * @date 2019-12-23
 * @time 09:56
 * @description
 */
public class JdbcUtilsDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();

        boolean flag = new JdbcUtilsDemo().login(username, password);
//        判断结果，输出不同语句
        if(flag){
            //登录成功
            System.out.println("登录成功！");
        }else{
            System.out.println("用户名或密码错误！");
        }
    }

    public boolean login(String username, String password){
        if(username == null || password == null){
            return false;
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
//            使用工具类中方法建立连接
            conn = JdbcUtils.getConnection();

            String sql = "select * from user where username = '" + username + "' and password ='" + password
                    +"'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
//            使用工具类中的方法
            JdbcUtils.close(stmt,conn,rs);
        }
        return false;
    }
}
