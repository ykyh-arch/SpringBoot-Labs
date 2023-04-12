package cn.iocoder.springboot.lab37.loggingtlog.consumer;

import cn.iocoder.springboot.lab37.loggingtlog.message.DemoMessage;
import com.yomahub.tlog.core.mq.TLogMqConsumerProcessor;
import com.yomahub.tlog.core.mq.TLogMqRunner;
import com.yomahub.tlog.core.mq.TLogMqWrapBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = DemoMessage.QUEUE)
public class DemoConsumer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(TLogMqWrapBean tLogMqWrapBean) {
        TLogMqConsumerProcessor.process(tLogMqWrapBean, new TLogMqRunner<DemoMessage>() {
            @Override
            public void mqConsume(DemoMessage message) {
                logger.info("监听到消息，线程编号:{} ，消息内容：{}]", Thread.currentThread().getId(), message);
            }
        });
    }

}
