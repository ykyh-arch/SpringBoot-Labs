package cn.iocoder.springboot.lab31.rocketmqdemo;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.RPCHook;

import java.util.List;

/**
 * 消费者示例，参考代码：https://help.aliyun.com/document_detail/150029.html?spm=a2c4g.11186623.2.15.7a4051afUIphuG
 *
 * @author jaquez
 * @date 2021/08/10 14:26
 **/
public class AliyunConsumerSample {

    // 阿里云的accessKey 与 secretKey
    private static RPCHook getAclRPCHook() {
        return new AclClientRPCHook(new SessionCredentials("xxxxx", "xxxxxx"));
    }

    /**
     * RocketMQ 支持两种消息模式：集群消费（Clustering）和广播消费（Broadcasting）。
     * 在集群消费下，同一条消息只会被相同消费者分组的一个消费者所消费。
     * 在广播消费下，同一条消息会被相同消费者分组的所有消费者所消费。
     * 在当前示例里，我们采用的是 DefaultMQPushConsumer 的默认消费方式，集群消费。
     */
    /**
     * Consumer最佳实践
     * 1、消费过程要做到幂等（即消费端去重）
     * 2、尽量使用批量方式消费方式，可以很大程度上提高消费吞吐量。
     * 3、优化每条消息消费过程
     */
    public static void main(String[] args) throws InterruptedException, MQClientException {
        // <1> 创建 DefaultMQPushConsumer 对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("GID_PRODUCER_GROUP_JAQUEZ_TEST", getAclRPCHook(), new AllocateMessageQueueAveragely(), true, null);
        // <2> 设置 RocketMQ Namesrv 地址，集群已;隔开
        consumer.setNamesrvAddr("http://MQ_INST_1702037966651832_BXrrVt4h.mq-internet-access.mq-internet.aliyuncs.com:80");
        consumer.setAccessChannel(AccessChannel.CLOUD);
        // CONSUME_FROM_FIRST_OFFSET ：每个 Topic 队列的第一条消息。
        // CONSUME_FROM_LAST_OFFSET ：每个 Topic 队列的最后一条消息。
        // CONSUME_FROM_TIMESTAMP ：每个 Topic 队列的指定时间开始的消息。
        // <3> 设置消费进度，从 Topic 最初位置开始
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // <4> 订阅 TopicTest 主题，消费者组的消费者实例必须订阅完全相同的 Topic + Tag 。
        consumer.subscribe("TOPIC_JAQUEZ_TEST", "*");
        // <5> 添加消息监听器，并发消费
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                // 返回成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // <6> 启动 producer 消费者
        consumer.start();
        // 打印 Consumer 启动完成
        System.out.printf("Consumer Started.%n");

    }
}
