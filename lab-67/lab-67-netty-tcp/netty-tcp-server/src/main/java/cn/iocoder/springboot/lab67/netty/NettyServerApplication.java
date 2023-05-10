package cn.iocoder.springboot.lab67.netty;


import cn.iocoder.springboot.lab67.netty.server.NettyServer;
import cn.iocoder.springboot.lab67.nettycore.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * NettyServer 启动类
 *
 */
@Slf4j
@Configuration
@SpringBootApplication
public class NettyServerApplication implements CommandLineRunner {

    @Autowired
    private NettyServer nettyServer;

    @Bean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
    }

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class,args);
    }
}