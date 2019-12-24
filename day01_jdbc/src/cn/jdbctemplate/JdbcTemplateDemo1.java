package cn.jdbctemplate;

import cn.datasource.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author sucre
 * @date 2019-12-24
 * @time 14:08
 * @description JdbcTemplate的使用
 *         * update():执行DML语句。增、删、改语句
 *         * queryForMap():查询结果将结果集封装为map集合，将列名作为key，将值作为value 将这条记录封装为一个map集合
 *             * 注意：这个方法查询的结果集长度只能是1
 *         * queryForList():查询结果将结果集封装为list集合
 *             * 注意：将每一条记录封装为一个Map集合，再将Map集合装载到List集合中
 *         * query():查询结果，将结果封装为JavaBean对象
 *             * query的参数：RowMapper
 *                 * 一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
 *                 * new BeanPropertyRowMapper<类型>(类型.class)
 *         * queryForObject：查询结果，将结果封装为对象
 *             * 一般用于聚合函数的查询
 */

public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
//        1.导入jar包
//        2.创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
//        3.调用方法
        String sql = "update account set balance = 5000 where id = ?";
        int count = template.update(sql,3);
        System.out.println(count);

    }
}
