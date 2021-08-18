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
 * 定时消息示例
 * 定时消息，是指消息发到 Broker 后，不能立刻被 Consumer 消费，要到特定的时间点或者等待特定的时间后才能被消费。
 * 不过，RocketMQ 暂时不支持任意的时间精度的延迟，而是固化了 18 个延迟级别。
 * @author Jaquez
 * @date 2021/08/16 15:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo03ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo03Producer producer;

    // 测试同步定时任务
    @Test
    public void testSyncSendDelay() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSendDelay(id, 3); // 延迟级别 3 ，即 10 秒后消费
        logger.info("[testSyncSendDelay][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    // 测试异步定时任务
    @Test
    public void asyncSendDelay() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.asyncSendDelay(id, 3,new SendCallback() {

            @Override
            public void onSuccess(SendResult result) {
                logger.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
            }

            @Override
            public void onException(Throwable e) {
                logger.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
            }

        });

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}
