package cn.iocoder.springboot.lab31.rocketmqdemo.producer;

import cn.iocoder.springboot.lab31.rocketmqdemo.message.Demo03Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 定时消息测试
 * @author Jaquez
 * @date 2021/08/17 17:09
 */
@Component
public class Demo03Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    // 同步定时任务
    public SendResult syncSendDelay(Integer id, int delayLevel) {
        // 创建 Demo03Message 消息
        Message message = MessageBuilder.withPayload(new Demo03Message().setId(id))
                .build();
        // 同步发送消息
        return rocketMQTemplate.syncSend(Demo03Message.TOPIC, message, 30 * 1000,
                delayLevel);
    }

    // 异步定时任务
    public void asyncSendDelay(Integer id, int delayLevel, SendCallback callback) {
        // 创建 Demo03Message 消息
        Message message = MessageBuilder.withPayload(new Demo03Message().setId(id))
                .build();
        // 同步发送消息
        rocketMQTemplate.asyncSend(Demo03Message.TOPIC, message, callback, 30 * 1000,
                delayLevel);
    }

}
