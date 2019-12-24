package cn.jdbctemplate;

import cn.datasource.JdbcUtils;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author sucre
 * @date 2019-12-24
 * @time 14:28
 * @description JDBCTemplate练习
 *
 * 需求
 *      1.修改1001号数据的salary为10000
 *      2.添加一条记录
 *      3.删除刚刚添加的记录
 *      4. 查询id为1001的记录，将其封装为Map集合
 *      5. 查询所有记录，将其封装为List
 *      6. 查询所有记录，将其封装为Emp对象的List集合
 *      7. 查询总记录数
 */

public class JdbcTemplateDemo2 {
    private static JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
        test7();
    }

    /**
     * 1.修改1001号数据的salary为10000
     */
    public static void test1(){
        String sql = "update emp set salary = 10000 where id = 1001";
        int count = template.update(sql);
        System.out.println(count);
    }

    /**
     * 2.添加一条记录
     */
    public static void test2(){
        String sql = "insert into emp(id,ename,dept_id) values(?,?,?)";
        int count = template.update(sql,1015,"郭靖",10);
        System.out.println(count);
    }

    /**
     * 3.删除刚刚添加的记录
     */
    public static void test3(){
        String sql = "delete from emp where id = ?";
        int count = template.update(sql,1015);
        System.out.println(count);
    }

    /**
     * 4. 查询id为1001的记录，将其封装为Map集合
     * 注意：该方法查询的结果集长度只能是1
     */
    public static void test4(){
        String sql = "select * from emp where id = ?";
        Map<String,Object> map = template.queryForMap(sql,1001);
        System.out.println(map);
    }

    /**
     * 5.查询所有记录，将其封装为List
     */
    public static void test5(){
        String sql = "select * from emp";
        List<Map<String,Object>> list = template.queryForList(sql);
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }

    /**
     * 6. 查询所有记录，将其封装为Emp对象的List集合
     */
    public static void test6(){
        String sql = "select * from emp";
        List<Emp> list = template.query(sql,new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    /**
     * 7. 查询总记录数
     */
    public static void test7(){
        String sql = "select count(id) from emp";
        Long tatol = template.queryForObject(sql,Long.class);
        System.out.println(tatol);
    }
}
