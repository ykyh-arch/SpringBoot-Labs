package cn.iocoder.springboot.lab45.apollodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        // 启动 Spring Boot 应用
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        // 查看 Environment
        Environment environment = context.getEnvironment();
        System.out.println(environment);
    }

}
