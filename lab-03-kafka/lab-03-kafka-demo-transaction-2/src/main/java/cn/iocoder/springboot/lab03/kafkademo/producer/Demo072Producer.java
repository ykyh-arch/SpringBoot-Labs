package cn.iocoder.springboot.lab03.kafkademo.producer;

import cn.iocoder.springboot.lab03.kafkademo.message.Demo072Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Component
public class Demo072Producer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 以事务方式同步发送消息
    @Transactional
    public void syncSend(Integer id, Runnable runner) throws ExecutionException, InterruptedException {
        // 创建 Demo07Message 消息
        Demo072Message message = new Demo072Message();
        message.setId(id);
        try {
            SendResult<String, Object> sendResult = kafkaTemplate.send(Demo072Message.TOPIC, message).get();
            logger.info("[doInOperations][发送编号：[{}] 发送结果：[{}]]", id, sendResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        runner.run();
    }

}
