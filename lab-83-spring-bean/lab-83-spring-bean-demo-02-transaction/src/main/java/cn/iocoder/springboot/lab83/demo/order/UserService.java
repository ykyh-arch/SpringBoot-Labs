package cn.iocoder.springboot.lab83.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService
 *
 * @author fw001
 * @date 2023/09/12 11:38
 **/
@Component
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void addUser() {
        System.out.println("--------UserService.addUser start");
        this.jdbcTemplate.update("insert into t_user(name) VALUES (?)", "张三");
        System.out.println("--------UserService.addUser end");
    }



}
