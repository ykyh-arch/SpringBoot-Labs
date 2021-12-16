package cn.iocoder.springboot.lab32.activemqdemo.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息生产者演示示例，主题方式，演示先启动消费者，再启动生产者
 *
 * @author jaquez
 * @date 2021/12/15 11:27
 **/
public class ActiveMQPublisher {

    /**
     * 默认用户名
     */
    public static final String USERNAME = "admin"; // ActiveMQConnection.DEFAULT_USER;
    /**
     * 默认密码
     */
    public static final String PASSWORD = "admin"; // ActiveMQConnection.DEFAULT_PASSWORD;
    /**
     * 默认连接地址
     */
    public static final String BROKER_URL = "tcp://192.168.177.4:61616"; // ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            // 创建连接
            Connection connection = connectionFactory.createConnection();
            // 开启连接
            connection.start();
            // 创建会话，不需要事务
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建 Topic，用作消费者订阅消息
            Topic myTestTopic = session.createTopic("topic_demo");
            // 消息生产者
            MessageProducer producer = session.createProducer(myTestTopic);
            // 发送消息
            for (int i = 1; i <= 3; i++) {
                TextMessage message = session.createTextMessage("发送消息 " + i);
                producer.send(myTestTopic, message);
            }

            // 关闭资源
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
