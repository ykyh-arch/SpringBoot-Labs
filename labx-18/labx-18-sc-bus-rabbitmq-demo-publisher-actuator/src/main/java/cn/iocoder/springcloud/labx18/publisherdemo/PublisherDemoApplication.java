package cn.iocoder.springcloud.labx18.publisherdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/05/21 22:33
 */
@SpringBootApplication
// @RemoteApplicationEventScan
public class PublisherDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublisherDemoApplication.class, args);
    }

}
