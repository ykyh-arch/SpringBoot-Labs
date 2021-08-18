package cn.iocoder.springboot.lab31.rocketmqdemo.producer;

import cn.iocoder.springboot.lab31.rocketmqdemo.Application;
import org.apache.rocketmq.client.producer.SendCallback;
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
 * 顺序消息示例
 * @author Jaquez
 * @date 2021/08/17 17:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo06ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo06Producer producer;

    // 测试同步发送顺序消息，单线程顺序消费每条消息。
    @Test
    public void testSyncSendOrderly() throws InterruptedException {
        // 发送多条消息
        for (int i = 0; i < 3; i++) {
            int id = 1024; // 固定成 1024 ，方便我们测试是否发送到相同消息队列
            SendResult result = producer.syncSendOrderly(id);
            logger.info("[testSyncSendOrderly][发送编号：[{}] 发送结果：[{}]]", id, result);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    // 测试异步发送顺序消息，单线程顺序消费每条消息
    @Test
    public void testASyncSendOrderly() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            int id = 1024; // 固定成 1024 ，方便我们测试是否发送到相同消息队列
            producer.asyncSendOrderly(id, new SendCallback() {

                @Override
                public void onSuccess(SendResult result) {
                    logger.info("[testASyncSendOrderly][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
                }

                @Override
                public void onException(Throwable e) {
                    logger.info("[testASyncSendOrderly][发送编号：[{}] 发送异常]]", id, e);
                }

            });
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    // 测试oneWay发送顺序消息，单线程消费每条消息
    @Test
    public void testOnewaySendOrderly() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            int id = 1024; // 固定成 1024 ，方便我们测试是否发送到相同消息队列
            producer.onewaySendOrderly(id);
            logger.info("[testOnewaySendOrderly][发送编号：[{}] 发送完成]", id);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}
