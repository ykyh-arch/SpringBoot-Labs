package cn.iocoder.springboot.lab54.eventdemo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

/**
 * 示例三
 *
 * @author jaquez
 * @date 2022/01/05 17:55
 **/
public class MyListener3 implements ApplicationListener<MyEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(MyEvent myEvent) {

        logger.info(String.format("%s监听到事件源：%s.", MyListener3.class.getName(), myEvent.getSource()));

    }
}
