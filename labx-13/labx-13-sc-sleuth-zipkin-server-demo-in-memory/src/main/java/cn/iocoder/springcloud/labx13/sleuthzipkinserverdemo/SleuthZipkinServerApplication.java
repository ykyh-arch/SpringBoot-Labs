package cn.iocoder.springcloud.labx13.sleuthzipkinserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/09/15 17:27
 */
@SpringBootApplication
@EnableZipkinServer // 目前已经废弃
public class SleuthZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleuthZipkinServerApplication.class, args);
    }

}
