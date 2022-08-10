package cn.iocoder.springcloud.labx15.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口类
 *
 * @author Jaquez
 * @date 2022/08/10 10:18
 */
@SpringBootApplication
public class Demo01Application {

    public static void main(String[] args) {
        System.setProperty("server.port", "18081"); // 端口 18081
        SpringApplication.run(Demo01Application.class, args);
    }

}
