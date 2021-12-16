package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.config.ActiveMQConfig;
import cn.iocoder.springboot.lab32.activemqdemo.message.BroadcastMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 广播消息生产者发送消息示例
 *
 * @author Jaquez
 * @date 2021/12/16 11:16
 */
@Component
public class BroadcastProducer {

    @Resource(name = ActiveMQConfig.BROADCAST_JMS_TEMPLATE_BEAN_NAME)
    private JmsMessagingTemplate jmsMessagingTemplate;

    // 同步发送消息
    public void syncSend(Integer id) {
        // 创建 BroadcastMessage 消息
        BroadcastMessage message = new BroadcastMessage();
        message.setId(id);
        // 同步发送消息
        jmsMessagingTemplate.convertAndSend(BroadcastMessage.TOPIC, message);
    }

}
