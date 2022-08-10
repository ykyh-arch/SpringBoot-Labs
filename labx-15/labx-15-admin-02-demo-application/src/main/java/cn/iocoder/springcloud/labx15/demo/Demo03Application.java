package cn.iocoder.springcloud.labx15.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口类
 *
 * @author Jaquez
 * @date 2022/08/10 10:18
 */
@SpringBootApplication
public class Demo03Application {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("server.port", "18083");  // 端口 18082
        SpringApplication.run(Demo03Application.class, args);
        Thread.sleep(1000000L);
    }

}
