package cn.iocoder.springboot.lab46.sentineldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 从 nacos 获取规则配置演示示例
 *
 * @author Jaquez
 * @date 2021/11/26 14:26
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 设置系统属性 project.name，提供给 Sentinel 读取
        System.setProperty("project.name", "demo-application");

        // 启动 Spring Boot 应用
        SpringApplication.run(Application.class, args);
    }

}
