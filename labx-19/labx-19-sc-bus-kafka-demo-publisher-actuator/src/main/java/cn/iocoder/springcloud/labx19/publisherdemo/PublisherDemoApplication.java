package cn.iocoder.springcloud.labx19.publisherdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/09/13 16:33
 */
@SpringBootApplication
// @RemoteApplicationEventScan
public class PublisherDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublisherDemoApplication.class, args);
    }

}
