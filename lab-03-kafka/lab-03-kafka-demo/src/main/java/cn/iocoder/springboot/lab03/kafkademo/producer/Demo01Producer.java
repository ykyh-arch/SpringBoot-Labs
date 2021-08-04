package cn.iocoder.springboot.lab03.kafkademo.producer;

import cn.iocoder.springboot.lab03.kafkademo.message.Demo01Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * 生成者，实现三种发送消息的方式：同步/异步、oneway
 * @author Jaquez
 * @date 2021/08/03 17:39
 */
@Component
public class Demo01Producer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    // 同步消息
    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        // 创建 Demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 同步发送消息，异步阻塞获取结果
        return kafkaTemplate.send(Demo01Message.TOPIC, message).get();
    }

    // 异步消息
    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {
        // 创建 Demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 异步发送消息
        return kafkaTemplate.send(Demo01Message.TOPIC, message);
    }

}
