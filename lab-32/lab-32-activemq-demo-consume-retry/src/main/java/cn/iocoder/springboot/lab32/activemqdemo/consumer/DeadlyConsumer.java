package cn.iocoder.springboot.lab32.activemqdemo.consumer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo05Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 死信消费者
 * ActiveMQ 并不会无限重新投递消息给 Consumer 重新消费，而是在默认情况下，达到 N 次重试次数时，Consumer 还是消费失败时，该消息就会进入到死信队列(默认为 "ActiveMQ.DLQ" 队列)。后续，我们可以通过对死信队列中的消息进行重发，来使得消费者实例再次进行消费。
 *
 * @author Jaquez
 * @date 2021/12/16 15:24
 */
@Component
public class DeadlyConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @JmsListener(destination = Demo05Message.DEADLY_QUEUE)
    public void onMessage(Demo05Message message) {
        logger.info("[onMessage][线程编号:{} 死信消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
