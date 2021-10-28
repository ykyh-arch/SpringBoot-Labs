package cn.iocoder.springboot.lab04.rabbitmqdemo.config;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo09Message;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitConfig 配置类
 *
 * @author Jaquez
 * @date 2021/10/28 13:55
 */
@Configuration
public class RabbitConfig {

    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {

        // 创建 Queue
        @Bean
        public Queue demo09Queue() {
            return new Queue(Demo09Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo09Exchange() {
            return new DirectExchange(Demo09Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // autoDelete: 是否自动删除
        }

        // 创建 Binding
        // Exchange：Demo09Message.EXCHANGE
        // Routing key：Demo09Message.ROUTING_KEY
        // Queue：Demo09Message.QUEUE
        @Bean
        public Binding demo09Binding() {
            return BindingBuilder.bind(demo09Queue()).to(demo09Exchange()).with(Demo09Message.ROUTING_KEY);
        }

    }

}
