package cn.iocoder.springboot.lab04.rabbitmqdemo.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

/**
 * 异常处理类
 *
 * @author Jaquez
 * @date 2021/10/29 14:47
 */
@Component
public class RabbitLoggingErrorHandler implements ErrorHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public RabbitLoggingErrorHandler(SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory) {
        rabbitListenerContainerFactory.setErrorHandler(this);
    }

    @Override
    public void handleError(Throwable t) {
        logger.error("[handleError][发生异常]]", t);
    }

}
