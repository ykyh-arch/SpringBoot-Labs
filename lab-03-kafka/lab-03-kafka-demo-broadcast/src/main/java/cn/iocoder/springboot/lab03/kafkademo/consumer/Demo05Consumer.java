package cn.iocoder.springboot.lab03.kafkademo.consumer;

import cn.iocoder.springboot.lab03.kafkademo.message.Demo05Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Demo05Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 这里使用了 Spring EL 表达式，在每个消费者分组的名字上，使用 UUID 生成其后缀。保证每个项目启动的消费者分组不同，以达到广播消费的目的。
    @KafkaListener(topics = Demo05Message.TOPIC,
            groupId = "demo05-consumer-group-" + Demo05Message.TOPIC + "-" + "#{T(java.util.UUID).randomUUID()}")
    public void onMessage(Demo05Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
