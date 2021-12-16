package cn.iocoder.springboot.lab32.activemqdemo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQConnectionFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * ActiveMQConfig 配置类
 *
 * @author Jaquez
 * @date 2021/12/16 15:11
 */
@Configuration
public class ActiveMQConfig implements ActiveMQConnectionFactoryCustomizer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void customize(ActiveMQConnectionFactory factory) {

        factory.setRedeliveryPolicy(new RedeliveryPolicy());
        // 打印重试策略
        logger.info("[customize][默认重试策略: {}]", factory.getRedeliveryPolicy());
    }

}
