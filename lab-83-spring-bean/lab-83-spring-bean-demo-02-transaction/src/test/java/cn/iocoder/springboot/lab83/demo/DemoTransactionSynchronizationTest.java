package cn.iocoder.springboot.lab83.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;

/**
 * DemoTransactionSynchronizationTest
 * 参考资料：https://mp.weixin.qq.com/s?__biz=MzA5MTkxMDQ4MQ==&mid=2648937564&idx=2&sn=549f841b7935c6f5f98957e4d443f893&chksm=88620c62bf1585746fe952444619e1a3c5781a310328d571ba25ceb0fe23512c4447606e4f31&cur_album_id=1318969124223238148&scene=189#wechat_redirect
 *
 * @author fw001
 * @date 2023/09/07 17:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoTransactionSynchronizationTest {

    JdbcTemplate jdbcTemplate;
    PlatformTransactionManager platformTransactionManager;

    @Before
    public void before() {
        // 定义一个数据源
        DataSource dataSource = DataSourceUtils.getDataSource();
        // 定义一个JdbcTemplate，用来方便执行数据库增删改查
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.platformTransactionManager = new DataSourceTransactionManager(dataSource);
        // 清空表
        this.jdbcTemplate.update("truncate table t_user");
    }

    @Test
    public void m0() throws Exception {
        System.out.println("PROPAGATION_REQUIRED start");
        //2.定义事务属性：TransactionDefinition，TransactionDefinition可以用来配置事务的属性信息，比如事务隔离级别、事务超时时间、事务传播方式、是否是只读事务等等。
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
        //3.开启事务：调用platformTransactionManager.getTransaction开启事务操作，得到事务状态(TransactionStatus)对象
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        this.addSynchronization("ts-1", 2);
        this.addSynchronization("ts-2", 1);
        //4.执行业务操作，下面就执行2个插入操作
        jdbcTemplate.update("insert into t_user (name) values (?)", "test1-1");
        jdbcTemplate.update("insert into t_user (name) values (?)", "test1-2");
        this.m1();
        //5.提交事务：platformTransactionManager.commit
        System.out.println("PROPAGATION_REQUIRED 准备commit");
        platformTransactionManager.commit(transactionStatus);
        System.out.println("PROPAGATION_REQUIRED commit完毕");

        System.out.println("after:" + jdbcTemplate.queryForList("SELECT * from t_user"));
    }

    public void m1() {
        System.out.println("PROPAGATION_REQUIRES_NEW start");
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        jdbcTemplate.update("insert into t_user (name) values (?)", "test2-1");
        jdbcTemplate.update("insert into t_user (name) values (?)", "test2-2");
        this.addSynchronization("ts-3", 2);
        this.addSynchronization("ts-4", 1);
        System.out.println("PROPAGATION_REQUIRES_NEW 准备commit");
        platformTransactionManager.commit(transactionStatus);
        System.out.println("PROPAGATION_REQUIRES_NEW commit完毕");
    }

    public void addSynchronization(final String name, final int order) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public int getOrder() {
                    return order;
                }

                @Override
                public void suspend() {
                    System.out.println(name + ":suspend");
                }

                @Override
                public void resume() {
                    System.out.println(name + ":resume");
                }

                @Override
                public void flush() {
                    System.out.println(name + ":flush");
                }

                @Override
                public void beforeCommit(boolean readOnly) {
                    System.out.println(name + ":beforeCommit:" + readOnly);
                }

                @Override
                public void beforeCompletion() {
                    System.out.println(name + ":beforeCompletion");
                }

                @Override
                public void afterCommit() {
                    System.out.println(name + ":afterCommit");
                }

                @Override
                public void afterCompletion(int status) {
                    System.out.println(name + ":afterCompletion:" + status);
                }
            });
        }
    }
}
