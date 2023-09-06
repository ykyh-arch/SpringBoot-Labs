package cn.iocoder.springboot.lab83.demo.annodemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * MainConfig
 *
 * @author fw001
 * @date 2023/08/31 18:15
 **/
@EnableTransactionManagement
@Configuration
@ComponentScan
public class MainConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceUtils.getDataSource();
    }

    // 定义一个JdbcTemplate,用来执行db操作
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // 定义我一个事物管理器
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) { //@2
        return new DataSourceTransactionManager(dataSource);
    }

}
