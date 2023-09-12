package cn.iocoder.springboot.lab83.demo.mqdemo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * DemoTest
 *
 * @author fw001
 * @date 2023/09/12 10:48
 **/
public class DemoTest {

    private AnnotationConfigApplicationContext context;
    private UserService userService;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void before() {
        this.context = new AnnotationConfigApplicationContext(MainConfig.class);
        userService = context.getBean(UserService.class);
        this.jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        // 清空表
        jdbcTemplate.update("truncate table t_user");
        jdbcTemplate.update("truncate table t_msg");
        jdbcTemplate.update("truncate table t_msg_order");
    }

    @Test
    public void test1() {
        this.userService.register(1L, "路人");
    }

    @Test
    public void test2() {
        this.userService.registerFail(1L, "张三");
    }

    @Test
    public void test3() {
        UserService1 userService1 = this.context.getBean(UserService1.class);
        userService1.nested();
    }

}
