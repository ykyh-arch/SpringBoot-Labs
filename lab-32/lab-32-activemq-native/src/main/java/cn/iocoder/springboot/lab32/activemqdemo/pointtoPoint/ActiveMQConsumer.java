package cn.iocoder.springboot.lab32.activemqdemo.pointtoPoint;

import javax.jms.*; // 使用 JMS API

import java.util.concurrent.TimeUnit;

/**
 * 消费者消费消息示例
 *
 * @author Jaquez
 * @date 2021/12/15 11:16
 */
public class ActiveMQConsumer {

    public static void main(String[] args) throws JMSException {
        // 创建连接
        Connection connection = ActiveMQProducer.getConnection();

        // 创建会话
        final Session session = ActiveMQProducer.getSession(connection);

        // 创建队列
        Queue queue = ActiveMQProducer.getQueue(session);

        // 创建 Consumer
        MessageConsumer consumer = session.createConsumer(queue);
        // 消息监听器
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(String.format("[线程：%s][消息编号：%s][消息内容：%s]",
                            Thread.currentThread(), textMessage.getJMSMessageID(), textMessage.getText()));
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        // 关闭
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException ignore) {
        }
        session.close();
        connection.close();
    }

}
