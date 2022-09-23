package cn.iocoder.springboot.lab18.shardingdatasource.shardingstrategy;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.underlying.common.config.properties.ConfigurationPropertyKey;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * InlineShardingStrategyTest
 *
 * @author jaquez
 * @date 2022/09/21 14:08
 **/
public class InlineShardingStrategyTest {

    private static DataSource dataSource;

    @Before
    public void init() throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/ds_iss?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        ds.setUsername("root");
        ds.setPassword("123456");
        /**
         * 1.配置真实数据源
         */
        Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();
        dataSourceMap.put("ds", ds);
        /**
         * 2、配置t_user分片规则
         */
        TableRuleConfiguration userRuleConfiguration = new TableRuleConfiguration("t_user", "ds.t_user_$->{0..1}");
        //设置t_user表的分片规则
        final InlineShardingStrategyConfiguration userTableShardingStrategy = new InlineShardingStrategyConfiguration("id", "t_user_$->{id % 2}");
        userRuleConfiguration.setTableShardingStrategyConfig(userTableShardingStrategy);
        /**
         * 3、加入表的分片规则
         */
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(userRuleConfiguration);
        /**
         * 4、配置一些属性
         */
        Properties props = new Properties();
        //输出sql
        props.put(ConfigurationPropertyKey.SQL_SHOW.getKey(), true);
        /**
         * 5、创建数据源
         */
        dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, props);
    }

    @Test
    public void test1() throws SQLException {
        String sql = "insert t_user (id,name) value (?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            for (long id = 1; id <= 4; id++) {
                int parameterIndex = 1;
                ps.setLong(parameterIndex++, id);
                ps.setString(parameterIndex++, "路人-" + id);
                ps.executeUpdate();
            }
        }
    }

    @Test
    public void test2() throws SQLException {
        String sql = "insert t_user (id,name) value (?,?), (?,?), (?,?), (?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            int parameterIndex = 1;
            for (long id = 1; id <= 4; id++) {
                ps.setLong(parameterIndex++, id);
                ps.setString(parameterIndex++, "路人-" + id);
            }
            System.out.println("count:" + ps.executeUpdate());
        }
    }

    @Test
    public void test3() throws SQLException {
        String sql = "select id,name from t_user";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                System.out.println(String.format("id:%s,name:%s",id,name));
            }
        }
    }

    @Test
    public void test4() throws SQLException {
        String sql = "select id,name from t_user where id = 1";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                System.out.println(String.format("id:%s,name:%s",id,name));
            }
        }
    }

    @Test
    public void test5() throws SQLException {
        String sql = "select id,name from t_user where id in (1,2)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                System.out.println(String.format("id:%s,name:%s", id, name));
            }
        }
    }

    @Test
    public void test6() throws SQLException {
        String sql = "select id,name from t_user where id != 1"; // 由于分片规则不知道查询的数据具体在哪个库哪个表，所以会路由到所有表。
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                System.out.println(String.format("id:%s,name:%s", id, name));
            }
        }
    }

    @Test // 报错，java.lang.IllegalStateException: Inline strategy cannot support this type sharding:RangeRouteValue
    public void test7() throws SQLException {
        String sql = "select id,name from t_user where id between 1 and 10";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                System.out.println(String.format("id:%s,name:%s", id, name));
            }
        }
    }
}
