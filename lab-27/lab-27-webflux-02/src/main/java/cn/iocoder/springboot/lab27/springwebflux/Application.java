package cn.iocoder.springboot.lab27.springwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * netty/undertow/Jetty/tomcat -> reactor -> webFlux
 * @author Jaquez
 * @date 2021/07/29 10:14
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
