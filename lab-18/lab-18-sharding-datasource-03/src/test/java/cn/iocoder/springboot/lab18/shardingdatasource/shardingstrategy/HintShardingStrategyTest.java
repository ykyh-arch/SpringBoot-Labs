package cn.iocoder.springboot.lab18.shardingdatasource.shardingstrategy;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.underlying.common.config.properties.ConfigurationPropertyKey;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * HintShardingStrategyTest
 *
 * @author jaquez
 * @date 2022/09/22 15:24
 **/
public class HintShardingStrategyTest {

    private static DataSource dataSource;

    @Before
    public void init() throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/ds_hss?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        ds.setUsername("root");
        ds.setPassword("123456");

        /**
         * 1.配置真实数据源
         */
        Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();
        dataSourceMap.put("ds", ds);

        /**
         * 2、配置 t_file分片规则
         */
        //逻辑表名
        final String logicTable = "t_user";
        //对应的实际表（3张）
        final String actualDataNodes = "ds.t_user_0,ds.t_user_1";
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration(logicTable, actualDataNodes);

        //强制分片策略配置
        HintShardingStrategyConfiguration hintShardingStrategyConfiguration = new HintShardingStrategyConfiguration(
                new HintShardingAlgorithm<Integer>() {
                    @Override
                    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Integer> shardingValue) {
                        final Object[] tables = availableTargetNames.toArray();

                        List<String> result = new ArrayList<>();
                        //HintManager.getInstance().addTableShardingValue放入的值都在shardingValue里面
                        final Collection<Integer> tableIndexList = shardingValue.getValues();
                        for (Integer tableIndex : tableIndexList) {
                            result.add((String) tables[tableIndex]);
                        }
                        return result;
                    }
                });
        tableRuleConfiguration.setTableShardingStrategyConfig(hintShardingStrategyConfiguration);
        /**
         * 3、加入表的分片规则
         */
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(tableRuleConfiguration);

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
        String sql = "select id,name,name from t_user";
        try (HintManager instance = HintManager.getInstance();) {
            //设置查询表的索引，addTableShardingValue(逻辑表名,值)
            instance.addTableShardingValue("t_user", 0);
            try (
                    Connection connection = dataSource.getConnection();
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

    @Test
    public void test2() throws SQLException {
        String sql = "select id,name,name from t_user";
        try (HintManager instance = HintManager.getInstance();) {
            //设置查询表的索引，addTableShardingValue(逻辑表名,值)
            instance.addTableShardingValue("t_user", 1);
            try (
                    Connection connection = dataSource.getConnection();
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

    @Test
    public void test3() throws SQLException {
        String sql = "select id,name,name from t_user";
        try (HintManager instance = HintManager.getInstance();) {
            //设置查询表的索引，addTableShardingValue(逻辑表名,值)
            instance.addTableShardingValue("t_user", 0);
            instance.addTableShardingValue("t_user", 1);
            try (
                    Connection connection = dataSource.getConnection();
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

}
