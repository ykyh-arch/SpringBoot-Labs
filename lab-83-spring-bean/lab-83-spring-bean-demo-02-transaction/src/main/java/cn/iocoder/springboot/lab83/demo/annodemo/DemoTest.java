package cn.iocoder.springboot.lab83.demo.annodemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * DemoTest
 *
 * @author fw001
 * @date 2023/09/06 14:18
 **/
public class DemoTest {

    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();

        UserService userService = context.getBean(UserService.class);
        // 先执行插入操作
        int count = userService.insertBatch(
                "java高并发系列",
                "mysql系列",
                "maven系列",
                "mybatis系列");
        System.out.println("插入成功（条）：" + count);
        // 然后查询一下
        System.out.println(userService.userList());
    }

}
