package cn.iocoder.springboot.lab27.springwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 开启servlet组件扫描
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
