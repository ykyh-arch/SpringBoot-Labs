package cn.iocoder.springboot.lab40.zipkindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
public class Application1 {

    public static void main(String[] args) {
        System.setProperty("spring.application.name", "demo-application-01");
        System.setProperty("server.port", "8080");
        SpringApplication.run(Application1.class, args);
    }

}
