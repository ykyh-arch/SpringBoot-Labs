package cn.iocoder.springboot.lab45.apollodemo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 数据源配置类
 *
 * @author jaquez
 * @date 2021/09/18 10:29
 **/
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfiguration {

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    Logger logger = LoggerFactory.getLogger(getClass());

    private final static String DATASOURCE_TAG = "db";

    @Autowired
    ApplicationContext context;

    @ApolloConfig
    Config config;

    @Bean("dataSource")
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource source = new DynamicDataSource();
        source.setTargetDataSources(Collections.singletonMap(DATASOURCE_TAG, dataSource()));
        return source;
    }

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent changeEvent) {
        Set changedKeys = changeEvent.changedKeys();
        if (changedKeys.contains("spring.datasource.url")) {
            DynamicDataSource source = context.getBean(DynamicDataSource.class);
            // 优雅关闭旧数据源
            asyncTerminate(source);
            // 设置新源
            source.setTargetDataSources(Collections.singletonMap(DATASOURCE_TAG, dataSource()));
            source.afterPropertiesSet();
            logger.info("动态切换数据源为：{}", config.getProperty("spring.datasource.url", ""));
        }
    }

    private void asyncTerminate(DataSource dataSource) {
        DataSourceTerminationTask task = new DataSourceTerminationTask(dataSource, scheduledExecutorService);
        // 执行任务
        scheduledExecutorService.schedule(task, 0, TimeUnit.MILLISECONDS);
    }

    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(config.getProperty("spring.datasource.url", ""));
        dataSource.setUsername(config.getProperty("spring.datasource.username", ""));
        dataSource.setPassword(config.getProperty("spring.datasource.password", ""));
        return dataSource;
    }

    class DynamicDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() { return DATASOURCE_TAG; }
    }

}
