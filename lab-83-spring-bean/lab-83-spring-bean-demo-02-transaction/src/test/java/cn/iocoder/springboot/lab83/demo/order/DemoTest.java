package cn.iocoder.springboot.lab83.demo.order;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * DemoTest
 *
 * @author fw001
 * @date 2023/09/12 14:23
 **/
public class DemoTest {

    private UserService userService;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void before() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        userService = context.getBean(UserService.class);
        this.jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        // 清空表
        jdbcTemplate.update("truncate table t_user");
    }

    @Test
    public void test() {
        this.userService.addUser();
    }

}
