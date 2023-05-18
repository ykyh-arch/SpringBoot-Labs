package cn.iocoder.springboot.lab54.eventdemo.eventdemo.config.listener;

import cn.iocoder.springboot.lab54.eventdemo.eventdemo.base.EventListener;
import cn.iocoder.springboot.lab54.eventdemo.eventdemo.user.UserRegisterSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * SendEmailOnUserRegisterSuccessListener 用户注册成功事件监听器 -> 负责给用户发送邮件
 *
 * @author jaquez
 * @date 2023/05/17 18:36
 **/
@Component
public class SendEmailOnUserRegisterSuccessListener implements EventListener<UserRegisterSuccessEvent> {

    @Override
    public void onEvent(UserRegisterSuccessEvent event) {
        System.out.println(
                String.format("给用户【%s】发送注册成功邮件!", event.getUserName()));
    }
}
