package cn.iocoder.springboot.lab34.actuatordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * httpTrace 演示测试，{@link InMemoryHttpTraceRepository  }
 * @author Jaquez
 * @date 2021/09/22 13:58
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
