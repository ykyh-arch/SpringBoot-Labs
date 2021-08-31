package cn.iocoder.springboot.lab78.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 本地锁测试入门（处理重复提交问题）
 * 利用自定义注解、Spring Aop、Guava Cache实现表单防重复提交
 * @author Jaquez
 * @date 2021/08/31 15:28
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
