package cn.iocoder.springboot.lab83.demo.mqdemo;

import cn.iocoder.springboot.lab83.demo.annodemo.DataSourceUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * MainConfig
 *
 * @author fw001
 * @date 2023/09/11 17:07
 **/
@ComponentScan
@EnableTransactionManagement
public class MainConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceUtils.getDataSource();
    }

    // 定义一个jdbcTemplate
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // 定义事务管理器transactionManager
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
