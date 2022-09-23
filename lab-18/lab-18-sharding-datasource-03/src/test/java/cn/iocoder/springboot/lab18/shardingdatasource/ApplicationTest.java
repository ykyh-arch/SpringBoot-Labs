package cn.iocoder.springboot.lab18.shardingdatasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ApplicationTest
 *
 * @author jaquez
 * @date 2022/09/23 15:45
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSource dataSource;

    @Test
    public void test1() throws Exception {
        String sql = "insert into t_order (order_id,user_id,price) values (?,?,?)";
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            // 插入4条数据测试,每个表会落入1条数据
            for (long user_id = 1; user_id <= 2; user_id++) {
                for (long order_id = 1; order_id <= 2; order_id++) {
                    int j = 1;
                    ps.setLong(j++, order_id);
                    ps.setLong(j++, user_id);
                    ps.setLong(j, 100);
                    logger.info("count:{}", ps.executeUpdate());
                }
            }
        }
    }

    @Test // 全路由
    public void test2() throws SQLException {
        String sql = "select count(*) from t_order";
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            long st = System.currentTimeMillis();
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logger.info("记录数：{}", rs.getInt(1));
            }
        }
    }

    @Test
    public void test3() throws SQLException {
        String sql = "select count(*) from t_order where user_id = 1";
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            long st = System.currentTimeMillis();
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logger.info("记录数：{}", rs.getInt(1));
            }
        }
    }

    @Test
    public void test4() throws SQLException {
        String sql = "select count(*) from t_order where user_id = 1 and order_id = 1";
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            long st = System.currentTimeMillis();
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logger.info("记录数：{}", rs.getInt(1));
            }
        }
    }

    @Test
    public void test5() throws SQLException {
        String sql = "select count(*) from t_order where user_id = 1 and order_id in (1,2)";
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            long st = System.currentTimeMillis();
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logger.info("记录数：{}", rs.getInt(1));
            }
        }
    }
}
