package cn.iocoder.springboot.lab54.eventdemo.event;

import org.springframework.context.ApplicationEvent;


/**
 * 申明一个事件
 *
 * 用户注册事件，继承 ApplicationEvent 事件，通过它的 source 属性可以获取事件源，timestamp 属性可以获得发生时间。
 *
 * @author Jaquez
 * @date 2022/01/05 11:43
 */
public class UserRegisterEvent extends ApplicationEvent {

    /**
     * 用户名
     */
    private String username;

    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
