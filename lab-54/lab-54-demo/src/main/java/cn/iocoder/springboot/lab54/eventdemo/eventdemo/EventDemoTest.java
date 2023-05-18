package cn.iocoder.springboot.lab54.eventdemo.eventdemo;

import cn.iocoder.springboot.lab54.eventdemo.eventdemo.config.EventConfig;
import cn.iocoder.springboot.lab54.eventdemo.eventdemo.user.UserRegisterService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * EventDemoTest 事件测试
 *
 * @author jaquez
 * @date 2023/05/17 18:27
 **/
public class EventDemoTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        // 获取用户注册服务
        UserRegisterService bean = context.getBean(UserRegisterService.class);
        // 模拟用户注册
        bean.registerUser("彰武");
    }
}
