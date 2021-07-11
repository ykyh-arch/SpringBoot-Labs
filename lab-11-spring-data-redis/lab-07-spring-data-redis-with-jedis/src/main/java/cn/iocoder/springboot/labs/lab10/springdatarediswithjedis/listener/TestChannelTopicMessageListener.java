package cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * 基于ChannelTopic实现Redis的订阅工呢
 * @author Jaquez
 * @date 2021/07/15 11:43
 */
public class TestChannelTopicMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("收到 ChannelTopic 消息：");
        System.out.println("线程编号：" + Thread.currentThread().getName());
        System.out.println("message：" + message);
        //主题内容
        System.out.println("pattern：" + new String(pattern));
    }

}
