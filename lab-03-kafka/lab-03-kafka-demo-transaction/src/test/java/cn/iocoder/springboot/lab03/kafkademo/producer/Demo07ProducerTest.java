package cn.iocoder.springboot.lab03.kafkademo.producer;

import cn.iocoder.springboot.lab03.kafkademo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo07ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo07Producer producer;

    // 测试同步发送消息，报错，原因：如果 Kafka Producer 开启了事务的功能，则所有发送的消息，都必须处于 Kafka 事务之中
    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    // 测试以事务的方式同步发送消息
    @Test
    public void testSyncSendInTransaction() throws ExecutionException, InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSendInTransaction(id, new Runnable() {

            @Override
            public void run() {
                logger.info("[run][我要开始睡觉了]");
                try {
                    Thread.sleep(10 * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                logger.info("[run][我睡醒了]");
            }

        });

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}
