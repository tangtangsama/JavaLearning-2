package cn.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sucre
 * @date 2019-12-20
 * @time 14:35
 * @description 数据库查询操作处理 ResultSet对象的使用
 */

public class JdbcDemo3 {

    public static void main(String[] args) {
        List<Emp> list = new JdbcDemo3().findAll();
        System.out.println(list);
        System.out.println(list.size());
    }

    /*
        将数据库对象封装成一个类，获取数据后写入到类中
        处理数据转为对类的实例的处理
     */

    public List<Emp> findAll(){
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Emp> list = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "select * from emp";
            conn = DriverManager.getConnection("jdbc:mysql:///db1?useSSL=false&serverTimezone=UTC","root","123456");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            Emp emp = null;
            list = new ArrayList<Emp>();

//          获取数据
            while(rs.next()){
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");

//              创建emp对象并赋值
                emp = new Emp();
                emp.setId(id);
                emp.setName(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);
//              装载集合
                list.add(emp);
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
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
