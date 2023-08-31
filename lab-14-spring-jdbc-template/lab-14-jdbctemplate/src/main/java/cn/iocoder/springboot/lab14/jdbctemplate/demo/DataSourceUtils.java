package cn.iocoder.springboot.lab14.jdbctemplate.demo;

import javax.sql.DataSource;

/**
 * DataSourceUtils
 *
 * @author fw001
 * @date 2023/08/31 10:22
 **/
public class DataSourceUtils {

    public static DataSource getDataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dbdemo?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(5);
        return dataSource;
    }
}
