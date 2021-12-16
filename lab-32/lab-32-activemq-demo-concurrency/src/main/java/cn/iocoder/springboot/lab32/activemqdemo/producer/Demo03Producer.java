package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo03Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 生产者生产消息示例
 *
 * @author Jaquez
 * @date 2021/12/16 14:09
 */
@Component
public class Demo03Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    // 同步发送消息
    public void syncSend(Integer id) {
        // 创建 Demo03Message 消息
        Demo03Message message = new Demo03Message();
        message.setId(id);
        // 同步发送消息
        jmsTemplate.convertAndSend(Demo03Message.QUEUE, message);
    }

}
