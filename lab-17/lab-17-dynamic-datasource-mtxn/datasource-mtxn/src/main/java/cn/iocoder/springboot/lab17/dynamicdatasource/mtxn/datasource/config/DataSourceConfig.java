package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.datasource.config;


import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.entity.enums.DataSourceType;

/**
 * 公共数据源配置信息
 */
public interface DataSourceConfig {
    // 数据源名称
    String getName();

    String getDbName();

    String getUsername();

    String getPassword();

    String getHost();

    Integer getPort();

    DataSourceType getType();

    String getJdbcUrl();

    String getDriverClass();

    String getTestSql();

    Integer getMinIdle();

    Integer getMaxPoolSize();

    Long getConnectionTimeout();
}