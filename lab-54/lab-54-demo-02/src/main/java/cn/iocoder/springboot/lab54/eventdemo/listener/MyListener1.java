package cn.iocoder.springboot.lab54.eventdemo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

/**
 * 示例一
 *
 * 自定义事件和自定义监听器类的实现方式：
 * 自定义事件：继承自 ApplicationEvent 抽象类，然后定义自己的构造器
 * 自定义监听：实现 ApplicationListener<T> 接口，然后实现 onApplicationEvent 方法
 *
 * @author jaquez
 * @date 2022/01/05 15:59
 **/
public class MyListener1 implements ApplicationListener<MyEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void onApplicationEvent(MyEvent event) {
        logger.info(String.format("%s监听到事件源：%s.", MyListener1.class.getName(), event.getSource()));
    }

}
