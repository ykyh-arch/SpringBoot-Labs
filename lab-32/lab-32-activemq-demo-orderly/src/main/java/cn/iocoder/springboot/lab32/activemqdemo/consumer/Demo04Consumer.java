package cn.iocoder.springboot.lab32.activemqdemo.consumer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo04Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消费者消费消息示例
 *
 * @author Jaquez
 * @date 2021/12/16 14:52
 */
@Component
public class Demo04Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 严格消费顺序消息
    @JmsListener(destination = Demo04Message.QUEUE_0)
    @JmsListener(destination = Demo04Message.QUEUE_1)
    @JmsListener(destination = Demo04Message.QUEUE_2)
    @JmsListener(destination = Demo04Message.QUEUE_3)
    public void onMessage(Demo04Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
