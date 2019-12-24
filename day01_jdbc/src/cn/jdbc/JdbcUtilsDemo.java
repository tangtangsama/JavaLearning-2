package cn.jdbc;

import java.sql.*;
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

        boolean flag = new JdbcUtilsDemo().login2(username, password);
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
            System.out.println(sql);
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

    /**
     * 输入用户随便，密码为：a' or 'a'='a 产生sql注入问题，改用PreparedStatement解决该问题
     * @param username
     * @param password
     * @return
     */
    public boolean login2(String username, String password){
        if(username == null || password == null){
            return false;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
//            使用工具类中方法建立连接
            conn = JdbcUtils.getConnection();

            String sql = "select * from user where username = ? and password =?";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);

//          给占位符?赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
//            使用工具类中的方法
            JdbcUtils.close(pstmt,conn,rs);
        }
        return false;
    }
}
