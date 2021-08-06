package cn.iocoder.springboot.lab03.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 参考：消费消息分区选择策略：https://www.iocoder.cn/Fight/How-do-Kafka-consumers-allocate-partitions/?self
 *      发送消息分区选择策略：https://leokongwq.github.io/2017/02/27/mq-kafka-producer-partitioner.html
 * 并发消费示例
 * 并发消费一个consumer对应一个线程，分配分区，同分区内串行消费
 * Spring-Kafka 提供的并发消费，会发现需要创建多个 Kafka Consumer 对象，并且每个 Consumer 都单独分配一个线程，然后 Consumer 拉取完消息之后，在各自的线程中执行消费。
 * 顺序消息
 * 普通顺序消息 ：Producer 将相关联的消息发送到相同的消息队列（主题）。
 * 完全严格顺序 ：在【普通顺序消息】的基础上，Consumer 严格顺序消费。
 * @author Jaquez
 * @date 2021/08/05 11:10
 */
@SpringBootApplication
public class Application {

    /**
     * 发送消息分区选择策略
     * 如果不手动指定分区选择策略类，则会使用默认的分区策略类。
     * 如果不指定消息的key，则消息发送到的分区是随着时间不停变换的（随机的）。
     * 如果指定了消息的key，则会根据消息的hash值和topic的分区数取模来获取分区的。
     * 如果应用有消息顺序性的需要，则可以通过指定消息的key和自定义分区类来将符合某种规则的消息发送到同一个分区。同一个分区消息是有序的，同一个分区只有一个消费者就可以保证消息的顺序性消费。
     */

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
