package cn.iocoder.springboot.lab03.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于发布/订阅的消息系统
 * 特点：同时为发布和订阅提供高吞吐量，消息持久化，分布式，消费消息采用 pull 模式，支持 online 和 offline 的场景
 * Kafka 基本概念参考：https://www.iocoder.cn/Kafka/yuliu/doc/
 * Zookeer 安装参考：https://www.cnblogs.com/pengpengdeyuan/p/14549505.html
 * Zookeer 极简入门参考：https://www.iocoder.cn/Zookeeper/install/?self
 * @author Jaquez
 * @date 2021/08/02 16:13
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
