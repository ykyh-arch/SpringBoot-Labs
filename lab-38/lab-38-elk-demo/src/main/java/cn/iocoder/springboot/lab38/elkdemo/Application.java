package cn.iocoder.springboot.lab38.elkdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ELK测试示例
 * 日志服务需要提供以下功能
 * 1、收集 ：能够采集多个来源的日志数据。
 * 2、传输 ：能够稳定的把日志数据传输到日志服务。
 * 3、存储 ：能够存储海量的日志数据。
 * 4、查询 ：能够灵活且高效的查询日志数据，并提供一定的分析能力。
 * 5、告警 ：能够提供提供告警功能，通知开发和运维等等。
 * 解决方案:
 * 开源（ELK、Apache Chukwa、Apache Kafka、Facebook Scribe）与商用（阿里云 SLS、腾讯云 CLS、华为云 LTS）
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
