package cn.iocoder.springboot.lab17.dynamicdatasource.transaction.service.impl;

import cn.iocoder.springboot.lab17.dynamicdatasource.transaction.service.TestService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 实现类
 *
 * @author jaquez
 * @date 2022/05/30 11:19
 **/
@Service
public class TestServiceImpl implements TestService {

    // 构造器注入
    private JdbcTemplate primaryJdbcTemplate;
    private JdbcTemplate secondaryJdbcTemplate;

    public TestServiceImpl(JdbcTemplate primaryJdbcTemplate, JdbcTemplate secondaryJdbcTemplate) {
        this.primaryJdbcTemplate = primaryJdbcTemplate;
        this.secondaryJdbcTemplate = secondaryJdbcTemplate;
    }

    @Transactional
    @Override
    public void testTransaction1() {

        // 修改 lab-17-transactions-1 库中的数据
        primaryJdbcTemplate.update("update user set age = ? where name = ?", 18, "Jaquez");
        // 修改 lab-17-transactions-2 库中的数据
        secondaryJdbcTemplate.update("update user set age = ? where name = ?", 18, "Jaquez");
    }

    @Transactional
    // 事务失败情况
    @Override
    public void testTransaction2() {

        // 修改 lab-17-transactions-1 库中的数据
        primaryJdbcTemplate.update("update user set age = ? where name = ?", 20, "Jaquez");
        // 模拟：修改 lab-17-transactions-2 库之前抛出异常
        throw new RuntimeException();

        // secondaryJdbcTemplate.update("update user set age = ? where name = ?", 20, "Jaquez");
    }
}
