package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo05Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 生产者发送消息示例
 *
 * @author Jaquez
 * @date 2021/12/16 15:22
 */
@Component
public class Demo05Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    // 同步发送消息
    public void syncSend(Integer id) {
        // 创建 ClusteringMessage 消息
        Demo05Message message = new Demo05Message();
        message.setId(id);
        // 同步发送消息
        jmsTemplate.convertAndSend(Demo05Message.QUEUE, message);
    }

}
