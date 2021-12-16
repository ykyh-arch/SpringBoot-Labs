package cn.iocoder.springboot.lab32.activemqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口类，消息模式：点对点、发布与订阅
 *
 * @author Jaquez
 * @date 2021/12/15 16:05
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
