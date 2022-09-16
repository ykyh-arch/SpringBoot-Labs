package cn.iocoder.springboot.lab86.demo.factory;

import cn.iocoder.springboot.lab86.demo.message.DemoMessageEvent;
import com.lmax.disruptor.EventFactory;

/**
 * MessageEventFactory 消息事件工厂类
 *
 * @author jaquez
 * @date 2022/09/16 11:31
 **/
public class MessageEventFactory implements EventFactory<DemoMessageEvent> {

    @Override
    public DemoMessageEvent newInstance() {
        return new DemoMessageEvent();
    }
}
