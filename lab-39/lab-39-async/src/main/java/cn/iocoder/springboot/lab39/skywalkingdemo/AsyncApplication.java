package cn.iocoder.springboot.lab39.skywalkingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 入口类
 *
 * @author Jaquez
 * @date 2021/12/22 14:15
 */
@SpringBootApplication
@EnableAsync(proxyTargetClass = true) // 开启 @Async 的支持，CGLIB 实现动态代理
public class AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }

}
