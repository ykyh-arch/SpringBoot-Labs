package cn.iocoder.springboot.lab31.rocketmqdemo;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 生产者示例
 *
 * @author jaquez
 * @date 2021/08/10 13:47
 **/
public class ProducerSample {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        // <1.1> 创建 DefaultMQProducer 对象
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // <1.2> 设置 RocketMQ Namesrv 地址，集群已;隔开
        producer.setNamesrvAddr("192.168.177.4:9876");
        // <1.3> 启动 producer 生产者
        producer.start();

        for (int i = 0; i < 100; i++) {
            try {
                // <2.1> 创建 Message 消息
                Message msg = new Message("TopicTest" /* Topic */,
                        "TagA" /* Tag */,
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );
                // <2.2> 同步发送消息
                SendResult sendResult = producer.send(msg,10000);
                // <2.3> 打印发送结果
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        // <3> 关闭 producer 生产者
        producer.shutdown();
    }
}
