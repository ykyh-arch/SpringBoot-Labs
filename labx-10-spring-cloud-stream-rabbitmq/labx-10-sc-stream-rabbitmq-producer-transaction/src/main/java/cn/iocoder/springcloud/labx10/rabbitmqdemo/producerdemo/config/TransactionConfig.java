package cn.iocoder.springcloud.labx10.rabbitmqdemo.producerdemo.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 配置类
 *
 * @author Jaquez
 * @date 2022/06/23 16:33
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    @Bean
    public RabbitTransactionManager rabbitTransactionManager(ConnectionFactory connectionFactory) {
        return new RabbitTransactionManager(connectionFactory);
    }

}
