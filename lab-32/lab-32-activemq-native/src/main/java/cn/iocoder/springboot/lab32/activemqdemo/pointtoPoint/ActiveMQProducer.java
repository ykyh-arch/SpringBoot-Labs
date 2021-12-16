package cn.iocoder.springboot.lab32.activemqdemo.pointtoPoint;

import org.apache.activemq.ActiveMQConnectionFactory; // 使用 ActiveMQ 的客户端实现
import org.apache.activemq.ScheduledMessage;

import javax.jms.*; // 使用 JMS API

/**
 * 生产者生产消息示例，点对点方式
 *
 * @author Jaquez
 * @date 2021/12/15 11:03
 */
public class ActiveMQProducer {

    private static final String BROKER_URL = "tcp://192.168.177.4:61616";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private static final String QUEUE_NAME = "queue_demo"; // 只有 QUEUE_NAME 需要共享给 ActiveMQConsumer

    public static void main(String[] args) throws JMSException {
        // 创建连接
        Connection connection = getConnection();

        // 创建会话
        Session session = getSession(connection);

        // 创建队列
        Queue queue = getQueue(session);

        // 创建 Producer
        MessageProducer producer = session.createProducer(queue);

        // 发送 3 条消息
        for (int i = 0; i < 3; i++) {
            Message message = session.createTextMessage("Hello World" + i);
            // 支持定时消息，每小时都会发生消息被投递10次，延迟1秒开始，每次间隔1秒
//            message.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, "0 * * * *");
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 1000);
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 1000);
//            message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 9);

            producer.send(message);
        }

        // 关闭
        session.close();
        connection.close();
    }

    // 创建连接
    public static Connection getConnection() throws JMSException {
        // 创建连接
        ConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        Connection connection = factory.createConnection();
        // 启动连接
        connection.start();
        return connection;
    }

    // 创建会话
    public static Session getSession(Connection connection) throws JMSException {
        // 第一个方法参数 transacted ，是否开启事务。这里设置为 false ，无需开启
        // 第二个方法参数 acknowledgeMode ，确认模式。这里设置为 AUTO_ACKNOWLEDGE ，自动确认。推荐阅读 https://my.oschina.net/thinwonton/blog/995291
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    // 创建队列
    public static Queue getQueue(Session session) throws JMSException {
        return session.createQueue(QUEUE_NAME);
    }

}
