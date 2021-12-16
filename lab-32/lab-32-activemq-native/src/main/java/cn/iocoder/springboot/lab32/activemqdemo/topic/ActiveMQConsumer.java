package cn.iocoder.springboot.lab32.activemqdemo.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消费者消费消息示例
 *
 * @author jaquez
 * @date 2021/12/15 11:58
 **/
public class ActiveMQConsumer {
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
            // 创建 Topic
            Topic myTestTopic = session.createTopic("topic_demo");

            MessageConsumer messageConsumer = session.createConsumer(myTestTopic);
            // 绑定消息监听器
            messageConsumer.setMessageListener(new MessageListener() {
                // @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("消费者1 接收到消息：" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            MessageConsumer messageConsumer2 = session.createConsumer(myTestTopic);
            // 绑定消息监听器
            messageConsumer2.setMessageListener(new MessageListener() {
                // @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("消费者2 接收到消息：" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            MessageConsumer messageConsumer3 = session.createConsumer(myTestTopic);
            messageConsumer3.setMessageListener(new MessageListener() {
                // @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("消费者3 接收到消息：" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            // 让主线程休眠100秒，使消息消费者对象能继续存活一段时间从而能监听到消息
            Thread.sleep(100 * 1000);
            // 关闭资源
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
