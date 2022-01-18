package cn.iocoder.springboot.lab61.catdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口类
 *
 * @author Jaquez
 * @date 2022/01/18 14:56
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        System.setProperty("server.port", "9093"); // 端口 9093
        SpringApplication.run(Application.class, args);
    }

}
