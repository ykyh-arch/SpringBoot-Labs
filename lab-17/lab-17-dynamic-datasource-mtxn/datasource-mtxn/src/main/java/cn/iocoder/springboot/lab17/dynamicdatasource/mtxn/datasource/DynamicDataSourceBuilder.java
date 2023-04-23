package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.application.Application;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.datasource.config.DataSourceConfig;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.entity.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 动态数据源构建器 --> 创建对应的 hikari/druid 数据源信息
 */
@Component
@Slf4j
public class DynamicDataSourceBuilder implements BeanClassLoaderAware {


    private static final int MIN_IDLE = 1;
    private static final int INITIAL_SIZE = 8;
    private static final int MAX_POOL_SIZE = 8;
    private static final long CONNECTION_TIMEOUT = 30000L;
    private static ClassLoader classLoader;

    private static final String CLOSE_ERROR = "数据库链接关闭失败:{}";


    private DynamicDataSourceBuilder() {
    }

    public static javax.sql.DataSource createHikariDataSource(DataSourceConfig config) {
        DataSourceBuilder<HikariDataSource> builder = DataSourceBuilder.create(classLoader).type(HikariDataSource.class)
                .username(config.getUsername()).password(config.getPassword());

        String jdbcUrl = String.format(config.getJdbcUrl(), config.getHost(), config.getPort(), config.getDbName());
        builder.driverClassName(config.getDriverClass()).url(jdbcUrl);

        HikariDataSource ds = builder.build();
        // 启动线程池不校验连接
        ds.setInitializationFailTimeout(-1);
        // 连接超时
        ds.setConnectionTimeout(config.getConnectionTimeout() != null ? config.getConnectionTimeout() : CONNECTION_TIMEOUT);
        // 最小连接池
        ds.setMinimumIdle(config.getMinIdle() != null ? config.getMinIdle() : MIN_IDLE);
        // 最大连接池
        ds.setMaximumPoolSize(config.getMaxPoolSize() != null ? config.getMaxPoolSize() : MAX_POOL_SIZE);
        return ds;
    }

    public static javax.sql.DataSource createDruidDataSource(DataSourceConfig config) {
        DataSourceBuilder<DruidDataSource> builder = DataSourceBuilder.create(classLoader).type(DruidDataSource.class)
                .username(config.getUsername()).password(config.getPassword());

        String jdbcUrl = String.format(config.getJdbcUrl(), config.getHost(), config.getPort(), config.getDbName());
        builder.driverClassName(config.getDriverClass()).url(jdbcUrl);

        DruidDataSource ds = builder.build();
        // 初始连接
        ds.setInitialSize(config.getMaxPoolSize() != null ? config.getMaxPoolSize() : INITIAL_SIZE);
        // 最小连接池
        ds.setMinIdle(config.getMinIdle() != null ? config.getMinIdle() : MIN_IDLE);
        // 最大连接池
        ds.setMaxActive(config.getMaxPoolSize() != null ? config.getMaxPoolSize() : MAX_POOL_SIZE);
        ds.setMaxWait(CONNECTION_TIMEOUT);
        ds.setName(config.getName());
        try {
            ds.setFilters("stat");
            ds.setConnectionProperties("druid.stat.slowSqlMillis=1000");
        } catch (SQLException e) {
            log.error(CLOSE_ERROR, e);
        }
        return ds;
    }

    /**
     * 创建 com_datasource 中的数据源，兼容 hikari/druid，可以继续扩展其他数据源连接池的支撑
     *
     * @param config
     * @return
     */
    public static javax.sql.DataSource create(DataSourceConfig config) {
        DataSourceProperties dataSourceProperties = Application.resolve(DataSourceProperties.class);
        switch (dataSourceProperties.getType().getName()) {
            case "com.alibaba.druid.pool.DruidDataSource":
                return createDruidDataSource(config);
            case "com.zaxxer.hikari.HikariDataSource":
                return createHikariDataSource(config);
            default:
                return null;

        }
    }

    /**
     * 测试数据源链接是否正常
     */
    public static boolean testConnection(DataSourceConfig config, javax.sql.DataSource dataSource) {

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(config.getTestSql())
        ) {
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * 验证配置是否有效，这里只测试了 HikariDataSource 数据源，补充添加对 DruidDataSource 数据源校验
     *
     * @param dataSource
     * @return
     */
    public static boolean validDataSource(DataSource dataSource) {
        DataSourceProperties dataSourceProperties = Application.resolve(DataSourceProperties.class);
        switch (dataSourceProperties.getType().getName()) {
            case "com.alibaba.druid.pool.DruidDataSource":
                return validDruidDataSource(dataSource);
            case "com.zaxxer.hikari.HikariDataSource":
                return validHikariDataSource(dataSource);
            default:
                return false;
        }

    }

    private static boolean validDruidDataSource(DataSource dataSource) {
        String jdbcUrl = String.format(dataSource.getJdbcUrl(), dataSource.getHost(), dataSource.getPort(), dataSource.getDbName());
        DruidDataSource ds = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            DataSourceBuilder<DruidDataSource> builder = DataSourceBuilder.create(classLoader).type(DruidDataSource.class)
                    .username(dataSource.getUsername()).password(dataSource.getPassword());

            builder.driverClassName(dataSource.getDriverClass()).url(jdbcUrl);
            ds = builder.build();

            // 初始连接
            ds.setInitialSize(INITIAL_SIZE);
            ds.setName(dataSource.getName());
            ds.setFilters("stat");
            ds.setConnectionProperties("druid.stat.slowSqlMillis=1000");
            // 连接超时
            ds.setTimeBetweenConnectErrorMillis(dataSource.getConnectionTimeout());
            // 最小连接池
            ds.setMinIdle(dataSource.getMinIdle());
            // 最大连接池
            ds.setMaxActive(dataSource.getMaxPoolSize());
            ds.setMaxWait(CONNECTION_TIMEOUT);

            connection = ds.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(dataSource.getTestSql());
            return resultSet.next();
        } catch (Exception e) {
            log.warn("验证 {} 链接失败", jdbcUrl);
            if (log.isWarnEnabled()) {
                log.warn("会话验证失败: {}", e.getMessage());
            }
            return false;
        } finally {
//              释放资源  --- 由于数据库链接非常的稀缺, 所以在操作完成后,记得释放资源, 都会放到 finally 代码块中
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    if (log.isWarnEnabled()) {
                        log.warn(CLOSE_ERROR, e.getMessage());
                    }
                }
                resultSet = null;
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    if (log.isWarnEnabled()) {
                        log.warn(CLOSE_ERROR, e.getMessage());
                    }
                }
                statement = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    if (log.isWarnEnabled()) {
                        log.warn(CLOSE_ERROR, e.getMessage());
                    }
                }
                connection = null;
            }
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }
    }

    public static boolean validHikariDataSource(DataSource dataSource) {
        String jdbcUrl = String.format(dataSource.getJdbcUrl(), dataSource.getHost(), dataSource.getPort(), dataSource.getDbName());
        HikariDataSource ds = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            DataSourceBuilder<HikariDataSource> builder = DataSourceBuilder.create(classLoader).type(HikariDataSource.class)
                    .username(dataSource.getUsername()).password(dataSource.getPassword());

            builder.driverClassName(dataSource.getDriverClass()).url(jdbcUrl);
            ds = builder.build();
            // 连接超时
            ds.setConnectionTimeout(dataSource.getConnectionTimeout());
            // 最小连接池
            ds.setMinimumIdle(dataSource.getMinIdle());
            // 最大连接池
            ds.setMaximumPoolSize(dataSource.getMaxPoolSize());

            connection = ds.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(dataSource.getTestSql());
            return resultSet.next();
        } catch (Exception e) {
            log.warn("验证 {} 链接失败", jdbcUrl);
            if (log.isWarnEnabled()) {
                log.warn("会话验证失败: {}", e.getMessage());
            }
            return false;
        } finally {
//              释放资源  --- 由于数据库链接非常的稀缺, 所以在操作完成后,记得释放资源, 都会放到 finally 代码块中
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    if (log.isWarnEnabled()) {
                        log.warn(CLOSE_ERROR, e.getMessage());
                    }
                }
                resultSet = null;
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    if (log.isWarnEnabled()) {
                        log.warn(CLOSE_ERROR, e.getMessage());
                    }
                }
                statement = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    if (log.isWarnEnabled()) {
                        log.warn(CLOSE_ERROR, e.getMessage());
                    }
                }
                connection = null;
            }
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        DynamicDataSourceBuilder.classLoader = classLoader;
    }
}