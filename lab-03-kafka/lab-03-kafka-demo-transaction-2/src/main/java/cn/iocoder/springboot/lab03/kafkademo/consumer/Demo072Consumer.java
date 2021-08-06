package cn.iocoder.springboot.lab03.kafkademo.consumer;

import cn.iocoder.springboot.lab03.kafkademo.message.Demo072Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author jaquez
 * @date 2021/08/06 10:28
 **/
@Component
public class Demo072Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = Demo072Message.TOPIC,
            groupId = "demo072-consumer-group-" + Demo072Message.TOPIC)
    public void onMessage(Demo072Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
