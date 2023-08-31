package cn.iocoder.springboot.lab83.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * UserService
 *
 * @author fw001
 * @date 2023/08/31 17:09
 **/
@Component
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TransactionTemplate transactionTemplate;

    // 模拟业务操作1
    public void bus1() {
        this.transactionTemplate.executeWithoutResult(transactionStatus -> {
            // 先删除表数据
            this.jdbcTemplate.update("delete from t_user");
            // 调用bus2
            this.bus2();
        });
    }

    // 模拟业务操作2
    public void bus2() {
        this.transactionTemplate.executeWithoutResult(transactionStatus -> {
            this.jdbcTemplate.update("insert into t_user (name) VALUE (?)", "java");
            this.jdbcTemplate.update("insert into t_user (name) VALUE (?)", "spring");
            this.jdbcTemplate.update("insert into t_user (name) VALUE (?)", "mybatis");
        });
    }

    // 查询表中所有数据
    public List userList() {
        return jdbcTemplate.queryForList("select * from t_user");
    }

}
