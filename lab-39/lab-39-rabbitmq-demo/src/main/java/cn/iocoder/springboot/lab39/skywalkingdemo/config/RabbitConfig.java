package cn.iocoder.springboot.lab39.skywalkingdemo.config;

import cn.iocoder.springboot.lab39.skywalkingdemo.message.DemoMessage;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 *
 * @author Jaquez
 * @date 2021/12/22 10:05
 */
@Configuration
public class RabbitConfig {

    // 创建 Queue
    @Bean
    public Queue demoQueue() {
        return new Queue(DemoMessage.QUEUE, // Queue 名字
                true, // durable: 是否持久化
                false, // exclusive: 是否排它
                false); // autoDelete: 是否自动删除
    }

    // 创建 Direct Exchange
    @Bean
    public DirectExchange demoExchange() {
        return new DirectExchange(DemoMessage.EXCHANGE,
                true,  // durable: 是否持久化
                false);  // exclusive: 是否排它
    }

    // 创建 Binding
    // Exchange：DemoMessage.EXCHANGE
    // Routing key：DemoMessage.ROUTING_KEY
    // Queue：DemoMessage.QUEUE
    @Bean
    public Binding demoBinding() {
        return BindingBuilder.bind(demoQueue()).to(demoExchange()).with(DemoMessage.ROUTING_KEY);
    }

}
