package cn.iocoder.springcloud.labx21.zuuldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/09/14 10:21
 */
@SpringBootApplication
@EnableZuulProxy // 开启 Zuul 网关
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
