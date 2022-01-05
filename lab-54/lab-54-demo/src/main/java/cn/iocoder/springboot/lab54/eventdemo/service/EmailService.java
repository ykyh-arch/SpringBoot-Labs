package cn.iocoder.springboot.lab54.eventdemo.service;

import cn.iocoder.springboot.lab54.eventdemo.event.UserRegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 邮箱服务，实现 ApplicationListener，进行指定类型的事件的监听。
 *
 * @author Jaquez
 * @date 2021/07/19 10:33
 */
@Service
public class EmailService implements ApplicationListener<UserRegisterEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Async
    public void onApplicationEvent(UserRegisterEvent event) {
        logger.info("[onApplicationEvent][给用户({}) 发送邮件]", event.getUsername());
    }

}
