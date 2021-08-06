package cn.iocoder.springboot.lab03.kafkademo.producer;

import cn.iocoder.springboot.lab03.kafkademo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo072ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo072Producer producer;

    // 测试以事务的方式同步发送消息
    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id, new Runnable() {

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
