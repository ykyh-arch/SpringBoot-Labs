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
 * 广播消息示例，注意与kafka的区别
 * 广播消费模式下，相同 Consumer Group 的每个 Consumer 实例都接收全量的消息。不过， Kafka 并不直接提供内置的广播消费的功能！！！此时，我们只能退而求其次，每个 Consumer 独有一个 Consumer Group ，从而保证都能接收到全量的消息。
 * @author Jaquez
 * @date 2021/08/17 16:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo05ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo05Producer producer;

    // 模拟Spring环境，启动一个消费者分组 "demo05-consumer-group-DEMO_05" 的 Consumer 节点
    @Test
    public void test() throws InterruptedException {
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    // 测试同步发送消息
    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}
