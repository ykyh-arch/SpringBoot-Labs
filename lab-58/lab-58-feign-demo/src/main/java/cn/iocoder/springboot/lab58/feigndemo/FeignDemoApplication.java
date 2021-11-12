package cn.iocoder.springboot.lab58.feigndemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Feign 调用演示示例，使用 Feign 是为了降低使用成本
 * Feign 使用参考：https://www.iocoder.cn/Fight/Feign-official-document-translation/?self
 *
 * @author Jaquez
 * @date 2021/11/07 15:49
 */
@SpringBootApplication
public class FeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignDemoApplication.class, args);
    }

}
