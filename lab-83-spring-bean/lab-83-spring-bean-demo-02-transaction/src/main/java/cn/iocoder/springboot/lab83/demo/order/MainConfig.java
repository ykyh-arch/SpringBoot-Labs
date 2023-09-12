package cn.iocoder.springboot.lab83.demo.order;

import cn.iocoder.springboot.lab83.demo.annodemo.DataSourceUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * MainConfig
 *
 * @author fw001
 * @date 2023/09/12 11:36
 **/
@Configuration // 说明当前类是一个配置类
@ComponentScan // 开启bean自动扫描注册功能
@EnableTransactionManagement(order = 2) // @1：设置事务拦截器的顺序是2
@EnableAspectJAutoProxy // @2：开启@Aspect Aop功能
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
