package cn.iocoder.springcloud.labx29.publisherdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/08/26 15:42
 */
@SpringBootApplication
// @RemoteApplicationEventScan
public class PublisherDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublisherDemoApplication.class, args);
    }

}
