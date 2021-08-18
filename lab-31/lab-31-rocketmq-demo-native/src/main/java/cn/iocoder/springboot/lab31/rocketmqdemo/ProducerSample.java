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

    /**
     * Producer最佳实践
     * 1、一个应用尽可能用一个 Topic，消息子类型用 tags 来标识，tags 可以由应用自由设置。只有发送消息设置了tags，消费方在订阅消息时，才可以利用 tags 在 broker 做消息过滤。
     * 2、每个消息在业务层面的唯一标识码，要设置到 keys 字段，方便将来定位消息丢失问题。由于是哈希索引，请务必保证 key 尽可能唯一，这样可以避免潜在的哈希冲突。
     * 3、消息发送成功或者失败，要打印消息日志，务必要打印 sendresult 和 key 字段。
     * 4、对于消息不可丢失应用，务必要有消息重发机制。例如：消息发送失败，存储到数据库，能有定时程序尝试重发或者人工触发重发。
     * 5、某些应用如果不关注消息是否发送成功，请直接使用sendOneWay方法发送消息
     */

    public static void main(String[] args) throws MQClientException, InterruptedException {
        // <1.1> 创建 DefaultMQProducer 对象
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // <1.2> 设置 RocketMQ Namesrv 地址，集群已;隔开
        producer.setNamesrvAddr("192.168.177.4:9876");
        // <1.3> 启动 producer 生产者，整个应用生命周期内，只需要初始化1次，会进行一些初始化工作
        // 如果没有指定namesrv地址，将会自动寻址
        // 启动定时任务：更新namesrv地址、从namsrv更新topic路由信息、清理已经挂掉的broker、向所有broker发送心跳…
        // 启动负载均衡的服务
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
        // <3> 关闭 producer 生产者，清理资源，关闭网络连接，注销自己
        producer.shutdown();
    }
}
