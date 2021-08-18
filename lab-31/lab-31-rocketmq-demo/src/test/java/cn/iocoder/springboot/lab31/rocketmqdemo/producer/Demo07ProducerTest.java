package cn.iocoder.springboot.lab31.rocketmqdemo.producer;

import cn.iocoder.springboot.lab31.rocketmqdemo.Application;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * 事务消息测试
 * 在分布式消息队列中，目前唯一提供完整的事务消息的，只有 RocketMQ 。因为提供了事务回查机制。
 * @author Jaquez
 * @date 2021/08/18 09:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo07ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo07Producer producer;

    // 测试以事务的方式发送消息
    @Test
    public void testSendMessageInTransaction() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.sendMessageInTransaction(id);
        logger.info("[testSendMessageInTransaction][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}
