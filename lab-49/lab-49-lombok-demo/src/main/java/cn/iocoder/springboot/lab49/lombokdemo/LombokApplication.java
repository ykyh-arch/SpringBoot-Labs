package cn.iocoder.springboot.lab49.lombokdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Lombok 的实现原理，基于 JSR269(Pluggable Annotation Processing API) 规范，
 * 自定义编译器注解处理器，用于在 Javac 编译阶段时，扫描使用到 Lombok 定义的注解的类，进行自定义的代码生成。
 * 参考：https://www.iocoder.cn/Fight/Lombok-installation-and-Spring-Boot-integration-with-Lombok/?self
 */
@SpringBootApplication
public class LombokApplication {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        SpringApplication.run(LombokApplication.class, args);
    }

}
