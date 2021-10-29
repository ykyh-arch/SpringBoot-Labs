package cn.iocoder.springboot.lab04.rabbitmqdemo.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

/**
 * 消费异常处理类
 *
 * @author Jaquez
 * @date 2021/10/29 14:45
 */
@Component("rabbitListenerErrorHandler")
public class RabbitListenerErrorHandlerImpl implements RabbitListenerErrorHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message,
                              ListenerExecutionFailedException exception) {
        // 打印异常日志
        logger.error("[handleError][amqpMessage:[{}] message:[{}]]", amqpMessage, message, exception);

        // 直接继续抛出异常，一定要继续抛出该异常，否则消费重试机制将失效。
        throw exception;
    }

}
