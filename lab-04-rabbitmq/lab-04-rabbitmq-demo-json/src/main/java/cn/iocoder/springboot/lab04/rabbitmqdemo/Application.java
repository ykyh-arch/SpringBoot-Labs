package cn.iocoder.springboot.lab04.rabbitmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MessageConverter 消息转换器测试，默认采用的是 SimpleMessageConverter，采用 Java 自带序列化方式，实现对 Java POJO 对象的序列化和反序列化，所以官方目前不是很推荐。
 * 有以下缺点：
 * 无法跨语言
 * 序列化后的字节数组太大
 * 序列化性能太低
 *
 * @author Jaquez
 * @date 2021/10/29 11:51
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
