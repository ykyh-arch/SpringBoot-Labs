package cn.iocoder.springboot.lab04.rabbitmqdemo.consumer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo15Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Demo15Message.QUEUE)
public class Demo15Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 因为查看具体消息内容，判断是不是真的使用 JSON 格式，所以采用 AMQP Message 接收消息
    @RabbitHandler(isDefault = true)
    public void onMessage(Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(),
                new String(message.getBody()));
    }

}
