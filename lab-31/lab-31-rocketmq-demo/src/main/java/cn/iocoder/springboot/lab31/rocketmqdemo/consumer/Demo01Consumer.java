package cn.iocoder.springboot.lab31.rocketmqdemo.consumer;

import cn.iocoder.springboot.lab31.rocketmqdemo.message.Demo01Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 一般情况下，我们建议一个消费者分组，仅消费一个 Topic 。这样做会有两个好处：
 * 每个消费者分组职责单一，只消费一个 Topic 。
 * 每个消费者分组是独占一个线程池，这样能够保证多个 Topic 隔离在不同线程池，保证隔离性，从而避免一个 Topic 消费很慢，影响到另外的 Topic 的消费。
 * @author Jaquez
 * @date 2021/08/16 11:15
 */
@Component
@RocketMQMessageListener(
        topic = Demo01Message.TOPIC,
        consumerGroup = "demo01-consumer-group-" + Demo01Message.TOPIC
)
public class Demo01Consumer implements RocketMQListener<Demo01Message> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Demo01Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
