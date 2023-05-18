package cn.iocoder.springboot.lab54.eventdemo.eventdemo.user;

import cn.iocoder.springboot.lab54.eventdemo.eventdemo.base.AbstractEvent;

/**
 * UserRegisterSuccessEvent 用戶注册成功事件
 *
 * @author jaquez
 * @date 2023/05/17 18:10
 **/
public class UserRegisterSuccessEvent extends AbstractEvent {

    // 用户名
    private String userName;

    public UserRegisterSuccessEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
