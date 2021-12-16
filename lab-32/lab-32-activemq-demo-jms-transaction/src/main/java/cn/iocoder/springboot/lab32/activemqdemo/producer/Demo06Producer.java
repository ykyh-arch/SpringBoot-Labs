package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.config.ActiveMQConfig;
import cn.iocoder.springboot.lab32.activemqdemo.message.Demo06Message;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 生产者发送消息示例
 *
 * @author Jaquez
 * @date 2021/12/16 15:22
 */
@Component
public class Demo06Producer {

    @Resource(name = ActiveMQConfig.JMS_TEMPLATE_BEAN_NAME)
    private JmsMessagingTemplate jmsMessagingTemplate;

    // 同步发送消息
    public void syncSend(Integer id) {
        // 创建 ClusteringMessage 消息
        Demo06Message message = new Demo06Message();
        message.setId(id);
        // 同步发送消息
        jmsMessagingTemplate.convertAndSend(Demo06Message.QUEUE, message);
    }

}
