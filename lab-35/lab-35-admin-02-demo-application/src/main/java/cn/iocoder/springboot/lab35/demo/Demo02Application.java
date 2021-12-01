package cn.iocoder.springboot.lab35.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 入口类
 *
 * @author Jaquez
 * @date 2021/11/30 14:41
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Demo02Application {

    public static void main(String[] args) {
        System.setProperty("server.port", "18082");  // 端口 18082
        SpringApplication.run(Demo02Application.class, args);
    }

}
