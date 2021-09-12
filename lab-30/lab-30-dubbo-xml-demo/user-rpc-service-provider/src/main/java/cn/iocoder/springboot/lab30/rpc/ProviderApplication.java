package cn.iocoder.springboot.lab30.rpc;

import org.apache.dubbo.rpc.filter.ExceptionFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.beans.ExceptionListener;

@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class ProviderApplication {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        SpringApplication.run(ProviderApplication.class, args);
    }
}
