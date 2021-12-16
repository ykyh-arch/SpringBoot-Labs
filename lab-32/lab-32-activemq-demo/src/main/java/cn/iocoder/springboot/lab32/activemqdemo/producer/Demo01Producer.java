package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo01Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * 生产者生产消息示例
 *
 * @author Jaquez
 * @date 2021/12/15 15:39
 */
@Component
public class Demo01Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    // 同步发送消息
    public void syncSend(Integer id) {
        // 创建 ClusteringMessage 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 同步发送消息
        jmsTemplate.convertAndSend(Demo01Message.QUEUE, message);
    }

    // 异步发送消息，因为 JmsMessagingTemplate 并未像 KafkaTemplate 或 RocketMQTemplate 直接提供了异步发送消息的方法，所以我们需要结合 Spring 异步调用来实现。
    @Async
    public ListenableFuture<Void> asyncSend(Integer id) {
        try {
            // 发送消息
            this.syncSend(id);
            // 返回成功的 Future
            return AsyncResult.forValue(null);
        } catch (Throwable ex) {
            // 返回异常的 Future
            return AsyncResult.forExecutionException(ex);
        }
    }

}
