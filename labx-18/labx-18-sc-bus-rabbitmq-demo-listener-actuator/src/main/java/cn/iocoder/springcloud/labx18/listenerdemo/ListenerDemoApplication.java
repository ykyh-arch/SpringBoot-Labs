package cn.iocoder.springcloud.labx18.listenerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/05/21 22:36
 */
@SpringBootApplication
@RemoteApplicationEventScan
public class ListenerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ListenerDemoApplication.class, args);
    }

}
