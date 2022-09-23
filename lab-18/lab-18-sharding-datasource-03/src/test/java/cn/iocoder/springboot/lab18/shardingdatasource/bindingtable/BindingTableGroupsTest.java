package cn.iocoder.springboot.lab18.shardingdatasource.bindingtable;

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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * BindingTableGroupsTest
 *
 * @author jaquez
 * @date 2022/09/22 16:19
 **/
public class BindingTableGroupsTest {

    private static DataSource dataSource;

    @Before
    public void init() throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/ds_order?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        ds.setUsername("root");
        ds.setPassword("123456");

        /**
         * 1.配置真实数据源
         */
        Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();
        dataSourceMap.put("ds", ds);

        /**
         * 2、配置2个表的分片规则
         */
        //t_order分片规则
        TableRuleConfiguration orderRuleConfiguration =
                new TableRuleConfiguration("t_order", "ds.t_order_$->{0..1}");
        InlineShardingStrategyConfiguration orderTableShardingStrategy =
                new InlineShardingStrategyConfiguration("order_id", "t_order_$->{(order_id + 1) % 2}");
        orderRuleConfiguration.setTableShardingStrategyConfig(orderTableShardingStrategy);

        //t_order_item分片规则
        TableRuleConfiguration orderItemRuleConfiguration =
                new TableRuleConfiguration("t_order_item", "ds.t_order_item_$->{0..1}");
        InlineShardingStrategyConfiguration orderItemTableShardingStrategy =
                new InlineShardingStrategyConfiguration("order_id", "t_order_item_$->{(order_id + 1) % 2}");
        orderItemRuleConfiguration.setTableShardingStrategyConfig(orderItemTableShardingStrategy);

        /**
         * 3、加入表的分片规则
         */
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderRuleConfiguration);
        shardingRuleConfig.getTableRuleConfigs().add(orderItemRuleConfiguration);
        shardingRuleConfig.setBindingTableGroups(Arrays.asList("t_order","t_order_item")); // 关联表

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
        String sql = "select a.order_id,b.id as order_item_id,b.price " +
                "from t_order a,t_order_item b " +
                "where a.order_id = b.order_id and a.order_id = 1";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Long order_id = rs.getLong("order_id");
                Long order_item_id = rs.getLong("order_item_id");
                Integer price = rs.getInt("price");
                System.out.println(String.format("order_id：%s，order_item_id：%s, price：%s", order_id, order_item_id, price));
            }
        }
    }

}
