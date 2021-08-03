package cn.iocoder.springboot.lab03.kafkademo;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 消费者示例
 * 先运行 Consumer ，这样当生产者发送消息的时候能在消费者后端看到消息记录
 * @author jaquez
 * @date 2021/08/02 18:10
 **/
public class ConsumerSample {

    public static void main(String[] args) {
        String topic = "testTopic";// topic name

        Properties props = new Properties();
        props.put("bootstrap.servers","192.168.177.4:9092");//用于建立与 kafka 集群连接的 host/port 组。
        props.put("group.id","testGroup");// Consumer Group Name
        props.put("enable.auto.commit","true");// Consumer 的 offset 是否自动提交
        props.put("auto.commit.interval.ms","1000");// 自动提交 offset 到 zookeeper 的时间间隔，时间是毫秒
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        Consumer<String, String> consumer = new KafkaConsumer(props);
        consumer.subscribe(Arrays.asList(topic));
        // 自旋，pull消息
        while(true)
        {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("partition = %d, offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
            }
        }
    }

}
