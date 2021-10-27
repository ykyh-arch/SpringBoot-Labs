package cn.iocoder.springboot.lab04.rabbitmqdemo.producer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo082Message;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo082Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 同步发送消息
    public void syncSend(Integer id) {
        // 创建 Demo082Message 消息
        Demo082Message message = new Demo082Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo082Message.EXCHANGE, Demo082Message.ROUTING_KEY, message, new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 设置消息 Header 过期时间
                message.getMessageProperties().setHeader("x-delay",3000);
                return message;
            }

        });
    }

}
