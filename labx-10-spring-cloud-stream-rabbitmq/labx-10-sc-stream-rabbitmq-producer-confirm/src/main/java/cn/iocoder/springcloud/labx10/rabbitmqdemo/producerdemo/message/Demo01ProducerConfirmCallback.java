package cn.iocoder.springcloud.labx10.rabbitmqdemo.producerdemo.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 监听 demo01-producer-confirm 这个 Channel，实现消息确认的监听
 *
 * @author Jaquez
 * @date 2022/06/23 17:57
 */
@Component
public class Demo01ProducerConfirmCallback {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ServiceActivator(inputChannel = "demo01-producer-confirm")
    public void onPublisherConfirm(Message message) {
        logger.info("[onPublisherConfirm][headers：{}]", message.getHeaders());
        logger.info("[onPublisherConfirm][payload：{}]", message.getPayload());
    }

}
