package cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.consumerdemo.listener;

import cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.consumerdemo.message.Demo01Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Demo01Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    @StreamListener(MySink.DEMO01_INPUT)
//    public void onMessage(@Payload Demo01Message message) {
//        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
//    }

    @StreamListener(value = MySink.DEMO01_INPUT, condition = "headers['rocketmq_TAGS'] == 'yunai'")
    public void onMessage(@Payload Demo01Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
