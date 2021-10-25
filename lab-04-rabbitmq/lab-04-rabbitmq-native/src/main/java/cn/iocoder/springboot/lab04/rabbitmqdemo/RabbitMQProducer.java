package cn.iocoder.springboot.lab04.rabbitmqdemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 安装指导：https://www.cnblogs.com/xueweihan/p/7099641.html
 * 基本概念
 * Override：整体一览。
 * Connections：网络连接，比如一个 TCP 连接。
 * Channels：信道，多路复用连接中的一条独立的双向数据流通道。信道是建立在真实的 TCP 连接内地虚拟连接，AMQP 命令都是通过信道发出去的，不管是发布消息、订阅队列还是接收消息，这些动作都是通过信道完成。因为对于操作系统来说建立和销毁 TCP 都是非常昂贵的开销，所以引入了信道的概念，以复用一条 TCP 连接。
 * Exchanges：交换器，用来接收生产者发送的消息并将这些消息路由给服务器中的队列。
 * Queues：消息队列，用来保存消息直到发送给消费者。它是消息的容器，也是消息的终点。一个消息可投入一个或多个队列。消息一直在队列里面，等待消费者连接到这个队列将其取走。
 * 生产者示例
 * @author Jaquez
 * @date 2021/10/25 10:16
 */
public class RabbitMQProducer {

    private static final String IP_ADDRESS = "192.168.177.4";
    private static final Integer PORT = 5672;
    private static final String USERNAME = "mqadmin";
    private static final String PASSWORD = "mqadmin";

    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routingkey_demo";
    public static final String QUEUE_NAME = "queue_demo"; // 只有 QUEUE_NAME 需要共享给 RabbitMQConsumer

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        Connection connection = getConnection();

        // 创建信道
        Channel channel = connection.createChannel();

        // 初始化测试用的 Exchange 和 Queue
        initExchangeAndQueue(channel);

        // 发送 3 条消息
        for (int i = 0; i < 3; i++) {
            String message = "Hello World" + i;
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        }

        // 关闭
        channel.close();
        connection.close();
    }

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        return factory.newConnection();
    }

    // 创建 RabbitMQ Exchange 和 Queue ，然后使用 ROUTING_KEY 路由键将两者绑定。
    // 该步骤，其实可以在 RabbitMQ Management 上操作，并不一定需要在代码中
    private static void initExchangeAndQueue(Channel channel) throws IOException {
        // 创建交换器：direct、持久化、不自动删除
        // Channel#exchangeDeclare(String exchange, String type, boolean durable, boolean autoDelete, Map<String, Object> arguments)
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);

        // 创建队列：持久化、非排他、非自动删除的队列
        // Channel#queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
    }

}
