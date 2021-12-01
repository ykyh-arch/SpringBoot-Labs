package cn.iocoder.springboot.lab35.adminserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 入口类
 *
 * @author Jaquez
 * @date 2021/11/30 14:55
 */
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }

}
