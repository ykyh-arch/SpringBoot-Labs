package cn.iocoder.springboot.lab04.rabbitmqdemo.config;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo082Message;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitConfig 配置类
 *
 * @author Jaquez
 * @date 2021/10/26 19:13
 */
@Configuration
public class RabbitConfig {

    /**
     * Custom Exchange 示例的配置类
     */
    public static class CustomExchangeDemoConfiguration {

        // 创建 Queue
        @Bean
        public Queue demo082Queue() {
            return
            new Queue(Demo082Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Custom Exchange，自定义交换机
        @Bean
        public CustomExchange demo082Exchange() {
            Map<String, Object> args = new HashMap<>();
            args.put("x-delayed-type", "direct");
            return new CustomExchange(Demo082Message.EXCHANGE,
                    "x-delayed-message",
                    true,  // durable: 是否持久化
                    false, // autoDelete: 是否自动删除
                    args);
        }

        // 创建 Binding
        // Exchange：Demo08Message.EXCHANGE
        // Routing key：Demo08Message.ROUTING_KEY
        // Queue：Demo08Message.QUEUE
        @Bean
        public Binding demo08Binding() {
            return BindingBuilder.bind(demo082Queue()).to(demo082Exchange()).with(Demo082Message.ROUTING_KEY).noargs();
        }

    }

}
