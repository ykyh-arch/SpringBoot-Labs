package cn.iocoder.springboot.lab18.shardingdatasource.shardingstrategy;

import com.google.common.collect.Range;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
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
 * StandardShardingStrategyTest
 *
 * @author jaquez
 * @date 2022/09/21 15:43
 **/
public class StandardShardingStrategyTest {

    private static DataSource dataSource;

    @Before
    public void init() throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/ds_sss?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        ds.setUsername("root");
        ds.setPassword("123456");
        /**
         * 1.配置真实数据源
         */
        Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();
        dataSourceMap.put("ds", ds);
        //创建一个Map用来存放：id范围和表名映射关系，路由的时候会根据这个信息来找到目标表
        Map<Range<Comparable>, String> idRangeTableNameMap = new HashMap<>();
        idRangeTableNameMap.put(Range.closed(1, 3), "t_user_0");
        idRangeTableNameMap.put(Range.atLeast(4), "t_user_1");
        System.out.println(idRangeTableNameMap);
        /**
         * 2、配置t_user分片规则
         */
        TableRuleConfiguration userRuleConfiguration = new TableRuleConfiguration("t_user", "ds.t_user_$->{0..1}");
        //设置 =,in 的算法策略
        PreciseShardingAlgorithm preciseShardingAlgorithm = new PreciseShardingAlgorithm() {
            @Override
            public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
                for (Map.Entry<Range<Comparable>, String> idRangeTableNameEntity : idRangeTableNameMap.entrySet()) {
                    final Range<Comparable> idRange = idRangeTableNameEntity.getKey();
                    final String tableName = idRangeTableNameEntity.getValue();
                    final Comparable id = shardingValue.getValue();
                    if (idRange.contains(id)) {
                        System.out.println(String.format("准确路由,id：%s, tableName：%s", id, tableName));
                        return tableName;
                    }
                }
                return null;
            }
        };
        //设置 BETWEEN AND, >, <, >=, <= 范围算法策略
        RangeShardingAlgorithm rangeShardingAlgorithm = new RangeShardingAlgorithm() {
            @Override
            public Collection<String> doSharding(Collection availableTargetNames, RangeShardingValue shardingValue) {
                List<String> tableNameList = new ArrayList<>();
                for (Map.Entry<Range<Comparable>, String> idRangeTableNameEntity : idRangeTableNameMap.entrySet()) {
                    final Range<Comparable> idRange = idRangeTableNameEntity.getKey();
                    final String tableName = idRangeTableNameEntity.getValue();
                    final Range valueRange = shardingValue.getValueRange();
                    //判断2个区间是否有交集
                    if (idRange.isConnected(valueRange)) {
                        tableNameList.add(tableName);
                    }
                }
                System.out.println(String.format("范围路由,id：%s, tableNameList：%s", shardingValue, tableNameList));
                return tableNameList;
            }
        };
        //配置标注路由策略
        final StandardShardingStrategyConfiguration userTableShardingStrategy =
                new StandardShardingStrategyConfiguration("id",
                        preciseShardingAlgorithm,
                        rangeShardingAlgorithm);
        //设置表的路由策略
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
            for (int id = 1; id <= 4; id++) {
                int parameterIndex = 1;
                ps.setInt(parameterIndex++, id);
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
            for (int id = 1; id <= 4; id++) {
                ps.setInt(parameterIndex++, id);
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
        String sql = "select id,name from t_user where id in (1,2,4)";
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

    @Test // 支持范围查询
    public void test7() throws SQLException {
        String sql = "select id,name from t_user where id between 1 and 2";
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

    @Test // 支持复合条件查询
    public void test8() throws SQLException {
        String sql = "select id,name from t_user where id between 1 and 2 or id>=4";
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
