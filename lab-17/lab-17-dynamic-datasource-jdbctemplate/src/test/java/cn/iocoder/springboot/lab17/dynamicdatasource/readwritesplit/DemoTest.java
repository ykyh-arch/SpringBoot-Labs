package cn.iocoder.springboot.lab17.dynamicdatasource.readwritesplit;

import cn.iocoder.springboot.lab17.dynamicdatasource.readwritesplit.base.DsType;
import cn.iocoder.springboot.lab17.dynamicdatasource.readwritesplit.demo.MainConfig;
import cn.iocoder.springboot.lab17.dynamicdatasource.readwritesplit.demo.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * DemoTest
 *
 * @author fw001
 * @date 2023/09/12 17:40
 **/
public class DemoTest {

    UserService userService;

    @Before
    public void before() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        this.userService = context.getBean(UserService.class);
    }

    @Test
    public void test1() {
        System.out.println(this.userService.getUserNameById(1, DsType.MASTER));
        System.out.println(this.userService.getUserNameById(1, DsType.SLAVE));
    }

    @Test
    public void test2() {
        long id = System.currentTimeMillis();
        System.out.println(id);
        this.userService.insert(id, "张三");
    }
}
