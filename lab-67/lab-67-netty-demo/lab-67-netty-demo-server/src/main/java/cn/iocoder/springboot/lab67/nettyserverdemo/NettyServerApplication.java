package cn.iocoder.springboot.lab67.nettyserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Netty 服务端启动类，概念入门参考：https://www.iocoder.cn/Fight/crazymakercircle/Netty-Bootstrap/?self
 *
 * @author Jaquez
 * @date 2022/01/26 15:49
 */
@SpringBootApplication
public class NettyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }

}
