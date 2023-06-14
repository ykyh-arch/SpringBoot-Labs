package cn.iocoder.springboot.lab54.eventdemo.springeventdemo;

import org.junit.Test;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * EventDemoTest 事件测试
 *
 **/
public class EventDemoTest {

    @Test
    public void test() {
        // 创建事件广播器
        ApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        // 注册事件监听器
        applicationEventMulticaster.addApplicationListener(new SendEmailOnOrderCreateListener());
        // 广播事件订单创建事件
        applicationEventMulticaster.multicastEvent(new OrderCreateEvent(this, 1L));
    }
}
