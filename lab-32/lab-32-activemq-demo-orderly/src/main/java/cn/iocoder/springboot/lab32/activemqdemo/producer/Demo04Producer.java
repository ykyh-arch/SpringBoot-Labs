package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo04Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 生产者发送消息示例
 *
 * @author Jaquez
 * @date 2021/12/16 14:34
 */
@Component
public class Demo04Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    // 同步发送消息
    public void syncSend(Integer id) {
        // 创建 Demo04Message 消息
        Demo04Message message = new Demo04Message();
        message.setId(id);
        // 同步发送消息
        jmsTemplate.convertAndSend(this.getQueue(id), message);
    }

    // 获取队列
    private String getQueue(Integer id) {
        return Demo04Message.QUEUE_BASE + (id % Demo04Message.QUEUE_COUNT);
    }

}
