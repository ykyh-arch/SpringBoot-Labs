package cn.iocoder.springboot.lab32.activemqdemo.consumer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo03Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消费者消费消息示例
 *
 * @author Jaquez
 * @date 2021/12/16 14:09
 */
@Component
public class Demo03Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 使用 Spring-JMS 实现多线程的并发消费的机制
    @JmsListener(destination = Demo03Message.QUEUE,
        concurrency = "2")
    public void onMessage(Demo03Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
