package cn.iocoder.springboot.lab18.shardingdatasource.javaapidemo;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.underlying.common.config.properties.ConfigurationPropertyKey;

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
 * Demo5
 *
 * @author jaquez
 * @date 2022/09/22 15:48
 **/
public class Demo5 {

    public static void main(String[] args) throws SQLException {
        /**
         * 1.配置真实数据源
         */
        Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();
        dataSourceMap.put("ds0", dataSource1());
        dataSourceMap.put("ds1", dataSource2());

        /**
         * 2、无分片规则
         */
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 这里需要指定t_dict为广播模式
        shardingRuleConfig.setBroadcastTables(Arrays.asList("t_dict"));
        /**
         * 4、配置一些属性
         */
        Properties props = new Properties();
        //输出sql
        props.put(ConfigurationPropertyKey.SQL_SHOW.getKey(), true);

        /**
         * 5、创建数据源
         */
        props.put(ConfigurationPropertyKey.SQL_SHOW.getKey(), true);
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, props);
        Connection connection = dataSource.getConnection();

        System.out.println("测试插入数据：");
        String sql = "insert into t_dict (code,k,v) values ('gender','男','1'),('gender','女','2')";
        PreparedStatement ps = connection.prepareStatement(sql);
        System.out.println("插入记录数：" + ps.executeUpdate());

        System.out.println("测试查询数据：");
        ps = connection.prepareStatement("select count(*) from t_dict");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("count:" + rs.getInt(1));
        }

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
