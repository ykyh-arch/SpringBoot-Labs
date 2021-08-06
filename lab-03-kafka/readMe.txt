Kafka ：发布订阅消息系统

生产者（procuder）发布消息 ->消息代理（kafka broker），需要指定主题（topic）、消息内容
对应发布消息时分区分配策略：
     * 如果不手动指定分区选择策略类，则会使用默认的分区策略类（RangeAsignor）。
     * 如果不指定消息的key，则消息发送到的分区是随着时间不停变换的（随机的）。
     * 如果指定了消息的key，则会根据消息的hash值和topic的分区数取模来获取分区的。
     * 如果应用有消息顺序性的需要，则可以通过指定消息的key和自定义分区类来将符合某种规则的消息发送到同一个分      区。同一个分区消息是有序的，同一个分区只有一个消费者就可以保证消息的顺序性消费。
对应的ZK上数据存储结构：
    主题 -> 分区（有序队列0、1、2、3...） -> 消息（offset）

消费者（consumer）消费消息，主动拉取（poll） <-消息代理（kafka broker）
对应消费消息时分区分配策略（多线程消费、并发消费，区别于集群消费：平均分摊消息）：
RangeAsignor - 范围策略
RoundRobinAssignor - 轮询策略，参考：https://www.iocoder.cn/Fight/How-do-Kafka-consumers-allocate-partitions/?self
消费者提交消费进度（offset）
对应的ZK上数据存储结构：
    _consumer_offset -> partion（有序队列0、1、2、3...） -> 消费数据的offset
