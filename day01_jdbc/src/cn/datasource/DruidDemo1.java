package cn.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author sucre
 * @date 2019-12-24
 * @time 11:09
 * @description Druid数据库连接池的使用
 */
public class DruidDemo1 {
    public static void main(String[] args) throws Exception {
//        1.导入jar包
//        2.定义配置文件,是properties形式的,可以叫任意名称，可以放在任意目录下
//        3.加载配置文件 使用类加载器获取资源路径
        Properties pro = new Properties();
        InputStream is = DruidDemo1.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);

//        4.获取连接池对象,通过工厂来来获取  DruidDataSourceFactory
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);

//        获取连接
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
