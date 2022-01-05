package cn.iocoder.springboot.lab54.eventdemo.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 *
 * @author jaquez
 * @date 2022/01/05 16:02
 **/
public class MyEvent  extends ApplicationEvent {

    public MyEvent(Object source) {
        super(source);
    }
}
