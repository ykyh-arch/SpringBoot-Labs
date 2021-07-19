package cn.iocoder.springboot.lab54.eventdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.EventObject;

/**
 * 观察者模式[事件处理系统]：观察者模式是软件设计模式的一种。在此种模式中，一个目标对象管理所有相依于它的观察者对象，并且在它本身的状态改变时主动发出通知。这通常透过呼叫各观察者所提供的方法来实现。
 * 发布订阅模式：属于广义上的观察者模式，在观察者模式的 Subject 和 Observer 的基础上，引入 Event Channel 这个中介，，进一步解耦。
 * @author Jaquez
 * @date 2021/07/19 10:11
 */
@SpringBootApplication
@EnableAsync // 开启 Spring 异步的功能
public class DemoApplication implements ApplicationListener<ApplicationEvent>, SmartApplicationListener {
    private Logger logger = LoggerFactory.getLogger(getClass());

//    EventObject jdk事件对象

    /**
     * Spring 内置事件
     * IOC 容器相关事件：Spring Context 可以简单理解成 IoC 容器。
     * ContextStartedEvent：Spring Context 启动完成事件。
     * ContextStoppedEvent：Spring Context 停止完成事件。
     * ContextClosedEvent：Spring Context 停止开始事件。
     * ContextRefreshedEvent：Spring Context 初始化或刷新完成事件。
     * 应用相关的事件：SpringApplicationEvent 是事件基类
     * ApplicationStartingEvent：Application 启动开始事件。
     * ApplicationEnvironmentPreparedEvent：Spring Environment 准备完成的事件。
     * ApplicationContextInitializedEvent：Spring Context 准备完成，但是 Bean Definition 未加载时的事件
     * ApplicationPreparedEvent：Spring Context 准备完成，但是未刷新时的事件。
     * ApplicationReadyEvent：Application 启动成功事件。
     * ApplicationFailedEvent：Application 启动失败事件。
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            logger.info("[ApplicationReadyEvent][({}) 启动完成]", ((ApplicationReadyEvent) event).getSpringApplication());
        } else if (event instanceof ApplicationFailedEvent) {
            logger.info("[ApplicationFailedEvent][({}) 启动失败]", ((ApplicationReadyEvent) event).getSpringApplication());
        } else if (event instanceof ContextClosedEvent) {
            logger.info("[ContextClosedEvent][({}) 关闭服务]", ((ApplicationReadyEvent) event).getSpringApplication());
        } else if (event instanceof ContextStoppedEvent) {
            logger.info("[ContextClosedEvent][({}) 停止服务完成]", ((ApplicationReadyEvent) event).getSpringApplication());
        }
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        // 事件类型
        return false;
    }
}
