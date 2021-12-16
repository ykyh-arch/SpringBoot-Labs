package cn.iocoder.springboot.lab32.activemqdemo.consumer;

import cn.iocoder.springboot.lab32.activemqdemo.config.ActiveMQConfig;
import cn.iocoder.springboot.lab32.activemqdemo.message.Demo06Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消费者消费消息示例，参考：https://houbb.github.io/2018/09/20/spring-activemq-04-transaction
 *
 * @author Jaquez
 * @date 2021/12/16 15:24
 */
@Component
public class Demo06Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 每个消息侦听器调用将在活动JMS事务中操作，并在侦听器执行失败时回滚消息接收（消息会重回队列，等待下次消费）。
    @JmsListener(destination = Demo06Message.QUEUE,
            containerFactory = ActiveMQConfig.JMS_LISTENER_CONTAINER_FACTORY_BEAN_NAME)
    public void onMessage(Demo06Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // <X> 注意，此处抛出一个 RuntimeException 异常，模拟消费失败，测试事务的作用
        throw new RuntimeException("我就是故意抛出一个异常");
    }

}
