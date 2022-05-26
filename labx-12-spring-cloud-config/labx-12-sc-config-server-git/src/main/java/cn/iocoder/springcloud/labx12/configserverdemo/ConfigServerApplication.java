package cn.iocoder.springcloud.labx12.configserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/05/23 11:11
 */
@SpringBootApplication
@EnableConfigServer // 开启 ConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
