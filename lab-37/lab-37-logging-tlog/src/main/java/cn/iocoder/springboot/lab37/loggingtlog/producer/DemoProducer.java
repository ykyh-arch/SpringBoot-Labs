package cn.iocoder.springboot.lab37.loggingtlog.producer;

import cn.iocoder.springboot.lab37.loggingtlog.message.DemoMessage;
import com.yomahub.tlog.core.mq.TLogMqWrapBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

@Component
public class DemoProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    // 同步发送消息
    public void syncSend(Integer id) {
        // 创建 DemoMessage 消息
        DemoMessage message = new DemoMessage();
        message.setId(id);
        // 需要用 TLogMqWrapBean 包装业务 bean 才可以生效
        TLogMqWrapBean<DemoMessage> tLogMqWrap = new TLogMqWrapBean(message);
        // 同步发送消息
        rabbitTemplate.convertAndSend(DemoMessage.EXCHANGE, DemoMessage.ROUTING_KEY, tLogMqWrap);
    }

    // 异步发送消息
    @Async
    public ListenableFuture<Void> asyncSend(Integer id) {
        try {
            this.syncSend(id); // 发送消息
            return AsyncResult.forValue(null); // 返回成功的 Future
        } catch (Throwable ex) {
            return AsyncResult.forExecutionException(ex); // 返回异常的 Future
        }
    }

}
