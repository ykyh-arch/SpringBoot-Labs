package cn.iocoder.springboot.lab54.eventdemo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 示例四
 *
 * @author jaquez
 * @date 2022/01/05 18:02
 **/
@Component
public class MyListener4 {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 自定义注解方式
    @EventListener
    public void listener(MyEvent event) {
        logger.info(String.format("%s监听到事件源：%s.", MyListener4.class.getName(), event.getSource()));
    }

}
