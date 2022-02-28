package cn.iocoder.springboot.lab74.batchdemo.config;

import cn.iocoder.springboot.lab74.batchdemo.datasource.DynamicDataSource;
import cn.iocoder.springboot.lab74.batchdemo.enums.DataSourceType;
import cn.iocoder.springboot.lab74.batchdemo.utils.SpringUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源的自定义配置，多数据源配置参考：https://blog.csdn.net/u011976388/article/details/85331523
 *
 * @author Jaquez
 * @date 2022/02/23 14:49
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * 创建 demo 数据源的配置对象，业务数据源属性对象
     */
    @Bean(name = "dmDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.demo") // 读取 spring.datasource.demo 配置到 DataSourceProperties 对象
    public DataSourceProperties demoDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建 demo 数据源，业务数据源
     */
    @Bean(name = "dmDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.demo.hikari") // 读取 spring.datasource.demo 配置到 HikariDataSource 对象
    public DataSource demoDataSource(@Qualifier("dmDataSourceProperties") DataSourceProperties demoDataSourceProperties) {
        // 创建 HikariDataSource 对象
        return createHikariDataSource(demoDataSourceProperties);
    }

    /**
     * 创建 quartz 数据源的配置对象
     */
    @Bean(name = "qzDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.quartz") // 读取 spring.datasource.quartz 配置到 DataSourceProperties 对象
    public DataSourceProperties quartzDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建 quartz 数据源
     */
    @Bean(name = "qzDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.quartz.hikari")
    @QuartzDataSource
    public DataSource quartzDataSource(@Qualifier("qzDataSourceProperties") DataSourceProperties quartzDataSourceProperties) {
        // 创建 HikariDataSource 对象
        return createHikariDataSource(quartzDataSourceProperties);
    }

    /**
     * 创建 batch 数据源的配置对象
     */
    @Bean(name = "btDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.batch") // 读取 spring.datasource.batch 配置到 DataSourceProperties 对象
    public DataSourceProperties batchDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建 batch 数据源
     */
    @Bean(name = "btDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.batch.hikari")
    public DataSource batchDataSource(@Qualifier("btDataSourceProperties") DataSourceProperties batchDataSourceProperties) {
        // 创建 HikariDataSource 对象
        return createHikariDataSource(batchDataSourceProperties);
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(@Qualifier(value = "dmDataSource") DataSource demoDataSource)
    {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.DEMO.name(), demoDataSource);
        setDataSource(targetDataSources, DataSourceType.QUARTZ.name(), "qzDataSource");
        setDataSource(targetDataSources, DataSourceType.BATCH.name(), "btDataSource");
        return new DynamicDataSource(demoDataSource, targetDataSources);
    }

    /**
     * 设置数据源
     *
     * @param targetDataSources 备选数据源集合
     * @param sourceName 数据源名称
     * @param beanName bean名称
     */
    public void setDataSource(Map<Object, Object> targetDataSources, String sourceName, String beanName)
    {
        try
        {
            DataSource dataSource = SpringUtils.getBean(beanName);
            targetDataSources.put(sourceName, dataSource);
        }
        catch (Exception e)
        {
        }
    }

    /**
     * HikariDataSource 数据源的配置
     *
     * @author Jaquez
     * @date 2022/02/23 15:13
     * @param properties
     * @return com.zaxxer.hikari.HikariDataSource
     */
    private static HikariDataSource createHikariDataSource(DataSourceProperties properties) {
        // 创建 HikariDataSource 对象
        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        // 设置线程池名
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

    @Bean("dsTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier(value = "dynamicDataSource") DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}
