package cn.iocoder.springboot.lab83.demo.service;

import cn.iocoder.springboot.lab83.demo.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * DemoTest
 *
 * @author fw001
 * @date 2023/08/31 17:21
 **/
public class DemoTest {

    // @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.bus1();
        System.out.println(userService.userList());
    }

}
