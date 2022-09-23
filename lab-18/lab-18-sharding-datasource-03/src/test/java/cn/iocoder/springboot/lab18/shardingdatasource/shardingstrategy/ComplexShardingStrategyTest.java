package cn.iocoder.springboot.lab18.shardingdatasource.shardingstrategy;

import com.google.common.collect.Range;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ComplexShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
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
 * ComplexShardingStrategyTest
 *
 * @author jaquez
 * @date 2022/09/21 16:50
 **/
public class ComplexShardingStrategyTest {

    private static DataSource dataSource;

    @Before
    public void init() throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/ds_css?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
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
        final String logicTable = "t_file";
        //对应的实际表（3张）
        final String actualDataNodes = "ds.t_file_0,ds.t_file_1,ds.t_file_2";
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration(logicTable, actualDataNodes);

        //混合分片策略配置
        ComplexShardingStrategyConfiguration complexShardingStrategyConfiguration = new ComplexShardingStrategyConfiguration(
                "id,storage_type", //2个分片字段（最终落到那个表，由这两个字段的值决定）
                new ComplexKeysShardingAlgorithm() { //混合分片算法
                    @Override
                    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
                        List<String> tableNames = new ArrayList<>();
                        //=、in 走这里
                        final Map columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
                        Collection<Integer> storage_types = (Collection<Integer>) columnNameAndShardingValuesMap.get("storage_type");
                        Collection<Long> ids = (Collection<Long>) columnNameAndShardingValuesMap.get("id");
                        if (storage_types != null) {
                            for (Integer storage_type : storage_types) {
                                if (storage_type == 0) {
                                    if (ids != null) {
                                        for (Long id : ids) {
                                            if (id % 2 == 0) {
                                                tableNames.add("t_file_0");
                                            } else {
                                                tableNames.add("t_file_1");
                                            }
                                        }
                                    } else {
                                        tableNames.add("t_file_0");
                                        tableNames.add("t_file_1");
                                    }
                                } else if (storage_type == 1) {
                                    tableNames.add("t_file_2");
                                }
                            }
                        }
                        // 范围的走这里,留给大家自己实现
                        final Map<String, Range<Comparable>> columnNameAndRangeValuesMap = shardingValue.getColumnNameAndRangeValuesMap();

                        System.out.println(String.format("路由信息,tableNames：%s, id值：%s, storage_type值：%s", tableNames, ids, storage_types));

                        return tableNames.isEmpty() ? availableTargetNames : tableNames;
                    }
                });
        tableRuleConfiguration.setTableShardingStrategyConfig(complexShardingStrategyConfiguration);
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
        String sql = "insert t_file (id,storage_type,name) value (?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            long id = 1;
            for (int storage_type = 0; storage_type <= 1; storage_type++) {
                for (; id <= storage_type * 2 + 2; id++) {
                    int parameterIndex = 1;
                    ps.setLong(parameterIndex++, id);
                    ps.setInt(parameterIndex++, storage_type);
                    ps.setString(parameterIndex++, "ShardingSphere高手笔记-" + id);
                    ps.executeUpdate();
                }
            }
        }
    }

    @Test
    public void test2() throws SQLException {
        String sql = "insert t_file (id,storage_type,name) value (?,?,?), (?,?,?), (?,?,?), (?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            int parameterIndex = 1;
            for (long id = 1; id <= 4; id++) {
                ps.setLong(parameterIndex++, id);
                ps.setInt(parameterIndex++, (int) (id % 2));
                ps.setString(parameterIndex++, "Spring高手系列-" + id);
            }
            System.out.println("count:" + ps.executeUpdate());
        }
    }

    @Test
    public void test3() throws SQLException {
        String sql = "select id,storage_type,name from t_file";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                final String storage_type = rs.getString("storage_type");
                System.out.println(String.format("id:%s,storage_type:%s,name:%s", id, storage_type, name));
            }
        }
    }

    @Test
    public void test4() throws SQLException {
        String sql = "select id,storage_type,name from t_file where storage_type = 0";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                final String storage_type = rs.getString("storage_type");
                System.out.println(String.format("id:%s,storage_type:%s,name:%s", id, storage_type, name));
            }
        }
    }

    @Test
    public void test5() throws SQLException {
        String sql = "select id,storage_type,name from t_file where id in (1,2,4)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                final String storage_type = rs.getString("storage_type");
                System.out.println(String.format("id:%s,storage_type:%s,name:%s", id, storage_type, name));
            }
        }
    }

    @Test
    public void test6() throws SQLException {
        String sql = "select id,storage_type,name from t_file where id != 1";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                final String storage_type = rs.getString("storage_type");
                System.out.println(String.format("id:%s,storage_type:%s,name:%s", id, storage_type, name));
            }
        }
    }

    @Test
    public void test7() throws SQLException {
        String sql = "select id,storage_type,name from t_file where id between 1 and 2";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                final String storage_type = rs.getString("storage_type");
                System.out.println(String.format("id:%s,storage_type:%s,name:%s", id, storage_type, name));
            }
        }
    }

    @Test
    public void test8() throws SQLException {
        String sql = "select id,storage_type,name from t_file where id between 1 and 20 or storage_type=0";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                final String storage_type = rs.getString("storage_type");
                System.out.println(String.format("id:%s,storage_type:%s,name:%s", id, storage_type, name));
            }
        }
    }
}
