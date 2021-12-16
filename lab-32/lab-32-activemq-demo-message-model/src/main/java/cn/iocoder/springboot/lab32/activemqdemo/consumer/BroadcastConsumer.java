package cn.iocoder.springboot.lab32.activemqdemo.consumer;

import cn.iocoder.springboot.lab32.activemqdemo.config.ActiveMQConfig;
import cn.iocoder.springboot.lab32.activemqdemo.message.BroadcastMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 广播消息消费者消费消息示例
 *
 * @author Jaquez
 * @date 2021/12/16 11:18
 */
@Component
public class BroadcastConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 广播消息模式
    @JmsListener(destination = BroadcastMessage.TOPIC,
            containerFactory = ActiveMQConfig.BROADCAST_JMS_LISTENER_CONTAINER_FACTORY_BEAN_NAME)
    public void onMessage(BroadcastMessage message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
