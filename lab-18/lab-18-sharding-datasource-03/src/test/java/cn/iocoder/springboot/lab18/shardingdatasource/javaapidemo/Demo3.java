package cn.iocoder.springboot.lab18.shardingdatasource.javaapidemo;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.underlying.common.config.properties.ConfigurationPropertyKey;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Demo3 - 单个数据库单张表操作
 *
 * @author jaquez
 * @date 2022/09/21 11:11
 **/
public class Demo3 {

    public static void main(String[] args) throws SQLException {
        /**
         * 1.配置真实数据源
         */
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds0", dataSource1());
        /**
         * 2、无分片规则
         */
        /**
         * 3、分片规则
         */
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        /**
         * 4、配置一些属性
         */
        Properties props = new Properties();
        //输出sql
        props.put(ConfigurationPropertyKey.SQL_SHOW.getKey(), true);
        /**
         * 5、创建数据源
         */
        DataSource dataSource = ShardingDataSourceFactory.
                createDataSource(dataSourceMap, shardingRuleConfig, props);
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement("insert into t_user (name) values (?)");
        ps.setString(1, "张三");
        System.out.println(ps.executeUpdate());
        connection.commit();
        ps.close();
        connection.close();
    }
    private static DataSource dataSource1() {
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://localhost:3306/sj_ds0?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        return dataSource1;
    }
}
