package cn.iocoder.springboot.lab54.eventdemo.eventdemo.config;

import cn.iocoder.springboot.lab54.eventdemo.eventdemo.base.DefaultEventMulticaster;
import cn.iocoder.springboot.lab54.eventdemo.eventdemo.base.EventListener;
import cn.iocoder.springboot.lab54.eventdemo.eventdemo.base.EventMulticaster;
import cn.iocoder.springboot.lab54.eventdemo.eventdemo.user.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * EventConfig 事件配置类
 *
 * @author jaquez
 * @date 2023/05/17 18:21
 **/
@Configuration
@ComponentScan
public class EventConfig {

    /**
     * 注册一个 bean：事件发布者
     *
     * @param eventListeners
     * @return
     */
    @Bean
    @Autowired(required = false)
    public EventMulticaster eventMulticaster(List<EventListener> eventListeners) {
        EventMulticaster eventPublisher = new DefaultEventMulticaster();
        if (eventListeners != null) {
            eventListeners.forEach(eventPublisher::addEventListener);
        }
        return eventPublisher;
    }

    /**
     * 注册一个 bean：用户注册服务
     *
     * @param eventMulticaster
     * @return
     */
    @Bean
    public UserRegisterService userRegisterService(EventMulticaster eventMulticaster) {
        UserRegisterService userRegisterService = new UserRegisterService();
        userRegisterService.setEventMulticaster(eventMulticaster);
        return userRegisterService;
    }
}
