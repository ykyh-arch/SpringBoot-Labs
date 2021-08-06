package cn.iocoder.springboot.lab03.kafkademo;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.internals.AbstractPartitionAssignor;
import org.apache.kafka.common.TopicPartition;

import java.security.SecureRandom;
import java.util.*;

/**
 * 自定义随机分片器，随机选择消费者分配
 * 参考文章：https://www.iocoder.cn/Fight/How-do-Kafka-consumers-allocate-partitions/?self
 * @author jaquez
 * @date 2021/08/06 17:02
 **/
public class SelfRandomAssignor extends AbstractPartitionAssignor {

    // Subscription表示订阅关系，key是消费者，value是订阅的topic
    // 主题与消费者的映射关系
    private Map<String, List<String>> consumersPerTopic(Map<String, Subscription> consumerMetadata) {
        Map<String, List<String>> res = new HashMap<>();
        for (Map.Entry<String, Subscription> subscriptionEntry : consumerMetadata.entrySet()) {
            String consumerId = subscriptionEntry.getKey();
            for (String topic : subscriptionEntry.getValue().topics()) {
                put(res, topic, consumerId);
            }
        }
        return res;
    }

    @Override
    public Map<String, List<TopicPartition>> assign(Map<String, Integer> partitionsPerTopic, Map<String, Subscription> subscriptions) {
        // 得到topic和订阅该topic的消费者集合（参考RangeAssignor中的consumersPerTopic()方法）
        Map<String, List<String>> consumersPerTopic = consumersPerTopic(subscriptions);
        Map<String, List<TopicPartition>> assignment = new HashMap<>();
        for (String memberId : subscriptions.keySet()) {
            assignment.put(memberId, new ArrayList<>());
        }
        // 遍历每个topic
        for (Map.Entry<String, List<String>> topicEntry : consumersPerTopic.entrySet()) {
            String topic = topicEntry.getKey();
            // 订阅当前topic的所有消费者集合
            List<String> consumersForTopic = topicEntry.getValue();
            int consumerSize = consumersForTopic.size();

            Integer numPartitionsForTopic = partitionsPerTopic.get(topic);
            if (numPartitionsForTopic == null) {
                continue;
            }
            // 当前topic下所有分区
            List<TopicPartition> partitions = AbstractPartitionAssignor.partitions(topic, numPartitionsForTopic);
            for (TopicPartition partition:partitions){
                // 随机选择一个消费者
                int rand = new SecureRandom().nextInt(consumerSize);
                // 得到随机选择的消费者
                String selectedConsumer = consumersForTopic.get(rand);
                // 给选择的消费者分配当前分区
                assignment.get(selectedConsumer).add(partition);
            }
        }
        System.out.println("分配结果: "+ JSON.toJSONString(assignment));
        return assignment;
    }

    @Override
    public void onAssignment(Assignment assignment, int generation) {
    }

    @Override
    public String name() {
        return "self-random";
    }
}
