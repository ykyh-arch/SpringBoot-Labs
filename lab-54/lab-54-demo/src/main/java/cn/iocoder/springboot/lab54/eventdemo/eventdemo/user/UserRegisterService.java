package cn.iocoder.springboot.lab54.eventdemo.eventdemo.user;

import cn.iocoder.springboot.lab54.eventdemo.eventdemo.base.EventMulticaster;

/**
 * UserRegisterService 用户注册服务
 *
 * @author jaquez
 * @date 2023/05/17 18:14
 **/
public class UserRegisterService {
    // 事件发布者
    private EventMulticaster eventMulticaster;

    /**
     * 注册用户
     *
     * @param userName 用户名
     */
    public void registerUser(String userName) {
        // 用户注册(将用户信息入库等操作)
        System.out.println(String.format("用户【%s】注册成功", userName));
        // 广播事件
        this.eventMulticaster.multicastEvent(new UserRegisterSuccessEvent(this, userName));
    }

    public EventMulticaster getEventMulticaster() {
        return eventMulticaster;
    }

    public void setEventMulticaster(EventMulticaster eventMulticaster) {
        this.eventMulticaster = eventMulticaster;
    }
}
