package cn.iocoder.springboot.lab18.shardingdatasource.masterslave;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.hint.HintManager;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * MasterSlaveTest
 *
 * @author jaquez
 * @date 2022/09/22 18:02
 **/
public class MasterSlaveTest {

    private static DataSource dataSource;

    @Before
    public void init() throws SQLException {
        /**
         * 1、配置真实数据源
         */
        HikariDataSource ds_master_0 = new HikariDataSource();
        ds_master_0.setDriverClassName("com.mysql.jdbc.Driver");
        ds_master_0.setJdbcUrl("jdbc:mysql://localhost:3306/ds_master_0?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        ds_master_0.setUsername("root");
        ds_master_0.setPassword("123456");

        HikariDataSource ds_slave_0 = new HikariDataSource();
        ds_slave_0.setDriverClassName("com.mysql.jdbc.Driver");
        ds_slave_0.setJdbcUrl("jdbc:mysql://localhost:3306/ds_slave_0?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        ds_slave_0.setUsername("root");
        ds_slave_0.setPassword("123456");

        HikariDataSource ds_master_1 = new HikariDataSource();
        ds_master_1.setDriverClassName("com.mysql.jdbc.Driver");
        ds_master_1.setJdbcUrl("jdbc:mysql://localhost:3306/ds_master_1?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        ds_master_1.setUsername("root");
        ds_master_1.setPassword("123456");

        HikariDataSource ds_slave_1 = new HikariDataSource();
        ds_slave_1.setDriverClassName("com.mysql.jdbc.Driver");
        ds_slave_1.setJdbcUrl("jdbc:mysql://localhost:3306/ds_slave_1?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        ds_slave_1.setUsername("root");
        ds_slave_1.setPassword("123456");

        // 将4个数据源加入 dataSourceMap
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds_master_0", ds_master_0);
        dataSourceMap.put("ds_slave_0", ds_slave_0);
        dataSourceMap.put("ds_master_1", ds_master_1);
        dataSourceMap.put("ds_slave_1", ds_slave_1);

        // 主从规则配置，就是配置主从关系，让系统知道哪个库是主库、他的从库列表是哪些？
        MasterSlaveRuleConfiguration master0SlaveRuleConfig =
                new MasterSlaveRuleConfiguration(
                        "ds0",
                        "ds_master_0",  //dataSourceMap中主库的key
                        Arrays.asList("ds_slave_0")); // dataSourceMap中ds_master_0从库的key
        // 配置读写分离规则
        MasterSlaveRuleConfiguration master1SlaveRuleConfig =
                new MasterSlaveRuleConfiguration(
                        "ds1",
                        "ds_master_1",  //dataSourceMap中主库的key
                        Arrays.asList("ds_slave_1")); // dataSourceMap中ds_master_1从库的key

        /**
         * 2、配置t_user分片规则
         */
        TableRuleConfiguration userTableRuleConfiguration =
                new TableRuleConfiguration("t_user", "ds$->{0..1}.t_user");
        //设置t_user表的分库规则
        final InlineShardingStrategyConfiguration userTableShardingStrategy =
                new InlineShardingStrategyConfiguration("id", "ds$->{(id+1) % 2}");
        userTableRuleConfiguration.setDatabaseShardingStrategyConfig(userTableShardingStrategy);

        /**
         * 3、创建分片配置对象ShardingRuleConfiguration
         */
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        //将userTableRuleConfiguration放入表规则配置列表
        shardingRuleConfig.getTableRuleConfigs().add(userTableRuleConfiguration);
        //设置主从规则配置
        shardingRuleConfig.setMasterSlaveRuleConfigs(Arrays.asList(master0SlaveRuleConfig, master1SlaveRuleConfig));

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

    /**
     * 无事务查询，无事务查询，会落入从库
     *
     * @throws SQLException
     */
    @Test
    public void test1() throws SQLException {
        String sql = "select id,name from t_user where id = 1";
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

    /**
     * 有事务查询，开启事务，直接读取数据，会落入从库
     *
     * @throws SQLException
     */
    @Test
    public void test2() throws SQLException {
        try (Connection connection = dataSource.getConnection();) {
            //手动开启事务
            connection.setAutoCommit(false);
            String sql = "select id,name from t_user where id = 2";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                System.out.println(String.format("id:%s,name:%s", id, name));
            }
            connection.commit();
        }
    }

    /**
     * 有事务，写入数据，然后查询，（写入 & 查询都落入主库）
     *
     * @throws SQLException
     */
    @Test
    public void test3() throws SQLException {
        try (Connection connection = dataSource.getConnection();) {
            connection.setAutoCommit(false);
            System.out.println("-----------插入id为3数据-----------");
            String sql = "insert into t_user values (3,'张三')";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();

            System.out.println("-----------查询刚插入的数据-----------");
            sql = "select id,name from t_user where id = 3";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                System.out.println(String.format("id:%s,name:%s", id, name));
            }

            System.out.println("上面id为3的在t_master_0,下面来看看读取id为2的，看看会读取主库还是从库？");
            System.out.println("-----------查询id为2的数据-----------");

            sql = "select id,name from t_user where id = 2";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                final long id = rs.getLong("id");
                final String name = rs.getString("name");
                System.out.println(String.format("id:%s,name:%s", id, name));
            }
            connection.commit();
        }
    }

    /**
     * 通过hintManager.setMasterRouteOnly()强制走主库
     *
     * @throws SQLException
     */
    @Test
    public void test4() throws SQLException {
        String sql = "select id,name from t_user where id = 1";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {

            HintManager hintManager = null;
            try {
                //通过HintManager.setMasterRouteOnly()强制走主库，注意在finally中释放HintManager.close();
                hintManager = HintManager.getInstance();
                hintManager.setMasterRouteOnly();

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    final long id = rs.getLong("id");
                    final String name = rs.getString("name");
                    System.out.println(String.format("id:%s,name:%s", id, name));
                }
            } finally {
                if (hintManager != null) {
                    hintManager.close();
                }
            }
        }
    }
}
