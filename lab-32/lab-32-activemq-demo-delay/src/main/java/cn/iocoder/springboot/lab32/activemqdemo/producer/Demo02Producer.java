package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo02Message;
import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 生产者发送消息示例
 *
 * @author Jaquez
 * @date 2021/12/16 11:38
 */
@Component
public class Demo02Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    // 延时发送消息
    public void syncSend(Integer id, Integer delay) {
        // 创建 Demo02Message 消息
        Demo02Message message = new Demo02Message();
        message.setId(id);
        // 创建 Header
        Map<String, Object> headers = null;
        if (delay != null && delay > 0) {
            headers = new HashMap<>();
            // ScheduledMessage.AMQ_SCHEDULED_DELAY 延迟投递的时间
            // ScheduledMessage.AMQ_SCHEDULED_PERIOD 重复投递的时间间隔
            // ScheduledMessage.AMQ_SCHEDULED_REPEAT 重复投递次数
            // ScheduledMessage.AMQ_SCHEDULED_CRON Cron表达式
            headers.put(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
        }
        // 同步发送消息
        jmsTemplate.convertAndSend(Demo02Message.QUEUE, message, headers);
    }

}
