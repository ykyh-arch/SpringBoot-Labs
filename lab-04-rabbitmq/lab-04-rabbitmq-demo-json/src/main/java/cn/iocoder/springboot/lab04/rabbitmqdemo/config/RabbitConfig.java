package cn.iocoder.springboot.lab04.rabbitmqdemo.config;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo15Message;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitConfig 配置类
 *
 * @author Jaquez
 * @date 2021/10/29 11:56
 */
@Configuration
public class RabbitConfig {

    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {

        // 创建 Queue
        @Bean
        public Queue demo15Queue() {
            return new Queue(Demo15Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo15Exchange() {
            return new DirectExchange(Demo15Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo15Message.EXCHANGE
        // Routing key：Demo15Message.ROUTING_KEY
        // Queue：Demo15Message.QUEUE
        @Bean
        public Binding demo15Binding() {
            return BindingBuilder.bind(demo15Queue()).to(demo15Exchange()).with(Demo15Message.ROUTING_KEY);
        }

    }

    // 容器中注入自定义的 MessageConverter bean 对象
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
