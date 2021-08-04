package cn.iocoder.springboot.lab03.kafkademo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.*;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

/**
 * 消费重试机制
 * Spring-Kafka 提供消费重试的机制。在消息消费失败的时候，Spring-Kafka 会通过消费重试机制，重新投递该消息给 Consumer ，让 Consumer 有机会重新消费消息，实现消费成功。
 * 当然，Spring-Kafka 并不会无限重新投递消息给 Consumer 重新消费，而是在默认情况下，达到 N 次重试次数时，Consumer 还是消费失败时，该消息就会进入到死信队列。
 */
@Configuration
public class KafkaConfiguration {

    // kafka异常处理器
    @Bean
    @Primary
    public ErrorHandler kafkaErrorHandler(KafkaTemplate<?, ?> template) {
        // 创建 DeadLetterPublishingRecoverer 对象，死信
        ConsumerRecordRecoverer recoverer = new DeadLetterPublishingRecoverer(template);
        // 创建 FixedBackOff 对象，设置重试次数3次，每隔10秒钟消费一次，最多消费3次
        BackOff backOff = new FixedBackOff(10 * 1000L, 3L);
        // 创建 SeekToCurrentErrorHandler 对象
        return new SeekToCurrentErrorHandler(recoverer, backOff);
    }

      // 批量消费重试异常处理器
//    @Bean
//    @Primary
//    public BatchErrorHandler kafkaBatchErrorHandler() {
//        // 创建 SeekToCurrentBatchErrorHandler 对象，没有创建DeadLetterPublishingRecoverer对象，说明暂时不支持死信队列的机制。
//        SeekToCurrentBatchErrorHandler batchErrorHandler = new SeekToCurrentBatchErrorHandler();
//        // 创建 FixedBackOff 对象
//        BackOff backOff = new FixedBackOff(10 * 1000L, 3L);
//        batchErrorHandler.setBackOff(backOff);
//        // 返回
//        return batchErrorHandler;
//    }

}
