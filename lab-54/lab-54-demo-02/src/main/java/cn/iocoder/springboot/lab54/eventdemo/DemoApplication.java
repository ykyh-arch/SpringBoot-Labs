package cn.iocoder.springboot.lab54.eventdemo;

import cn.iocoder.springboot.lab54.eventdemo.listener.MyEvent;
import cn.iocoder.springboot.lab54.eventdemo.listener.MyListener1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * SpringBoot 事件监听的4种实现方式，参考自：https://mp.weixin.qq.com/s/_y6PC4VFa1wlGlprtuRs1A
 *
 * SpringBoot 进行事件监听有四种方式：
 * 一、手工向 ApplicationContext 中添加监听器
 * 二、将监听器装载入 spring 容器
 * 三、在 application.properties 中配置监听器
 * 四、通过 @EventListener 注解实现事件监听
 *
 * @author Jaquez
 * @date 2021/07/19 10:11
 */
@SpringBootApplication
public class DemoApplication  {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        // 方式一：装载监听
        context.addApplicationListener(new MyListener1());
        // 发布事件
        context.publishEvent(new MyEvent("测试事件."));

    }


}
