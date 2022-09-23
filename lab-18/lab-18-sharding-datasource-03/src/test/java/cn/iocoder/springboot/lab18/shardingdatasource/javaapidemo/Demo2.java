package cn.iocoder.springboot.lab18.shardingdatasource.javaapidemo;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
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
 * Demo2 - 多个数据库多张表操作
 *
 * @author jaquez
 * @date 2022/09/20 15:04
 **/
public class Demo2 {
    public static void main(String[] args) throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds0", dataSource1());
        dataSourceMap.put("ds1", dataSource2());
        /**
         * 2.配置表的规则
         */
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order", "ds$->{0..1}.t_order_$->{0..1}");
        // 指定db的分片策略（分片字段+分片算法）
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds$->{user_id % 2}"));
        // 指定表的分片策略（分片字段+分片算法）
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_$->{order_id % 2}"));
        /**
         * 3、分片规则
         */
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        //将表的分片规则加入到分片规则列表
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
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
        /**
         * 6、获取连接，执行sql
         */
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement("insert into t_order (order_id,user_id,price) values (?,?,?)");
        // 插入4条数据测试,每个表会落入1条数据
        for (long user_id = 1; user_id <= 2; user_id++) {
            for (long order_id = 1; order_id <= 2; order_id++) {
                int j = 1;
                ps.setLong(j++, order_id);
                ps.setLong(j++, user_id);
                ps.setLong(j, 100);
                System.out.println(ps.executeUpdate());
            }
        }
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
    private static DataSource dataSource2() {
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc:mysql://localhost:3306/sj_ds1?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");
        return dataSource2;
    }
}
