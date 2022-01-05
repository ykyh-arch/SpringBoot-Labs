package cn.iocoder.springboot.lab54.eventdemo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 示例二
 *
 * @author jaquez
 * @date 2022/01/05 16:11
 **/
@Component
public class MyListener2 implements ApplicationListener<MyEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(MyEvent myEvent) {

        logger.info(String.format("%s监听到事件源：%s.", MyListener2.class.getName(), myEvent.getSource()));

    }
}
