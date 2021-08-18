package cn.iocoder.springboot.lab31.rocketmqdemo.producer;

import cn.iocoder.springboot.lab31.rocketmqdemo.message.Demo06Message;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 在 RocketMQ 中，Producer 可以根据定义 MessageQueueSelector 消息队列选择策略，选择 Topic 下的队列。目前提供三种策略：
 * SelectMessageQueueByHash ，基于 hashKey 的哈希值取余，选择对应的队列。
 * SelectMessageQueueByRandom ，基于随机的策略，选择队列。
 * SelectMessageQueueByMachineRoom ，😈 有点看不懂，目前是空的实现，暂时无视吧。
 * 未使用 MessageQueueSelector 时，采用轮询的策略，选择队列。
 */
@Component
public class Demo06Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    // 同步发送顺序消息
    public SendResult syncSendOrderly(Integer id) {
        // 创建 Demo06Message 消息
        Demo06Message message = new Demo06Message();
        message.setId(id);
        // 同步发送消息，传入hashKey，最终会分配到相同的队列里，符合简单顺序消费
        return rocketMQTemplate.syncSendOrderly(Demo06Message.TOPIC, message, String.valueOf(id));
    }

    // 异步发送顺序消息
    public void asyncSendOrderly(Integer id, SendCallback callback) {
        // 创建 Demo06Message 消息
        Demo06Message message = new Demo06Message();
        message.setId(id);
        // 异步发送消息
        rocketMQTemplate.asyncSendOrderly(Demo06Message.TOPIC, message, String.valueOf(id), callback);
    }

    // oneway发送顺序消息
    public void onewaySendOrderly(Integer id) {
        // 创建 Demo06Message 消息
        Demo06Message message = new Demo06Message();
        message.setId(id);
        // 异步发送消息
        rocketMQTemplate.sendOneWayOrderly(Demo06Message.TOPIC, message, String.valueOf(id));
    }

}
