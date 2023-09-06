package cn.iocoder.springboot.lab83.demo.annodemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * UserService
 *
 * @author fw001
 * @date 2023/09/06 14:17
 **/
@Component
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 先清空表中数据，然后批量插入数据，要么都成功要么都失败
    @Transactional
    public int insertBatch(String... names) {
        int result = 0;
        jdbcTemplate.update("truncate table t_user");
        for (String name : names) {
            result += jdbcTemplate.update("INSERT INTO t_user(name) VALUES (?)", name);
        }
        return result;
    }

    // 获取所有用户信息
    public List<Map<String, Object>> userList() {
        return jdbcTemplate.queryForList("SELECT * FROM t_user");
    }

}
