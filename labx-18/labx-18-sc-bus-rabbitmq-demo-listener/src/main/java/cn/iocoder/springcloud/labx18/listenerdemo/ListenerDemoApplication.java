package cn.iocoder.springcloud.labx18.listenerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/05/21 19:19
 */
@SpringBootApplication
@RemoteApplicationEventScan // 声明要从 Spring Cloud Bus 监听 RemoteApplicationEvent 事件。
public class ListenerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ListenerDemoApplication.class, args);
    }

}
