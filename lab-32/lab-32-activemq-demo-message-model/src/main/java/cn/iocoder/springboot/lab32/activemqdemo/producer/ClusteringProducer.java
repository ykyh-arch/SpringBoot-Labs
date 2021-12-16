package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.config.ActiveMQConfig;
import cn.iocoder.springboot.lab32.activemqdemo.message.ClusteringMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 集群消费生成者发送消息示例
 *
 * @author Jaquez
 * @date 2021/12/16 10:42
 */
@Component
public class ClusteringProducer {

    @Resource(name = ActiveMQConfig.CLUSTERING_JMS_TEMPLATE_BEAN_NAME)
    private JmsMessagingTemplate jmsTemplate;

    // 同步发送消息
    public void syncSend(Integer id) {
        // 创建 ClusteringMessage 消息
        ClusteringMessage message = new ClusteringMessage();
        message.setId(id);
        // 同步发送消息
        jmsTemplate.convertAndSend(ClusteringMessage.QUEUE, message);
    }

}
