package cn.iocoder.springboot.lab03.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.listener.ContainerProperties;

/**
 * 消费进度的提交机制ACK，分为自动提交与手动提交
 * ContainerProperties#AckMode.java
 * RECORD：每条消息被消费完成后，自动提交
 * BATCH：每一次消息被消费完成后，在下次拉取消息之前，自动提交
 * TIME：达到一定时间间隔后，自动提交。不过要注意，它并不是一到就立马提交，如果此时正在消费某一条消息，需要等这条消息被消费完成，才能提交消费进度。
 * COUNT：消费成功的消息数到达一定数量后，自动提交。不过要注意，它并不是一到就立马提交，如果此时正在消费某一条消息，需要等这条消息被消费完成，才能提交消费进度。
 * COUNT_TIME：TIME 和 COUNT 的结合体，满足任一都会自动提交。
 * MANUAL：调用时，先标记提交消费进度。等到当前消息被消费完成，然后在提交消费进度。
 * MANUAL_IMMEDIATE：调用时，立即提交消费进度。
 * @author Jaquez
 * @date 2021/08/05 19:14
 */
@SpringBootApplication
public class Application {

    /**
     * 存在原生 Kafka 和 Spring-Kafka 提供的两种消费进度的提交机制，我们应该怎么配置呢？
     * 使用原生 Kafka 的方式，通过配置 spring.kafka.consumer.enable-auto-commit=true 。
     * 然后，通过 spring.kafka.consumer.auto-commit-interval 设置自动提交的频率。
     * 使用 Spring-Kafka 的方式，通过配置 spring.kafka.consumer.enable-auto-commit=false 。
     * 然后通过 spring.kafka.listener.ack-mode 设置具体模式。
     * 另外，还有 spring.kafka.listener.ack-time 和 spring.kafka.listener.ack-count 可以设置自动提交的时间间隔和消息条数。
     */

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
