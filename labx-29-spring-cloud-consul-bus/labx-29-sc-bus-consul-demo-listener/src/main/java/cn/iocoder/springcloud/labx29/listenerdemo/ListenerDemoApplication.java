package cn.iocoder.springcloud.labx29.listenerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/08/30 11:35
 */
@SpringBootApplication
@RemoteApplicationEventScan
public class ListenerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ListenerDemoApplication.class, args);
    }

}
