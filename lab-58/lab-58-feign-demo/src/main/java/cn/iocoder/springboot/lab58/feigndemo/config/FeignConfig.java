package cn.iocoder.springboot.lab58.feigndemo.config;

import cn.iocoder.springboot.lab58.feigndemo.feign.UserServiceFeignClient;
import cn.iocoder.springboot.lab58.feigndemo.feign.UserServiceFeignClient02;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.spring.SpringContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FeignConfig 配置类
 *
 * @author Jaquez
 * @date 2021/11/08 11:12
 */
@Configuration
public class FeignConfig {

    @Bean
    public UserServiceFeignClient userServiceFeignClient() {
        return Feign.builder()
                .encoder(new GsonEncoder()) // 编码器
                .decoder(new GsonDecoder())  // 解码器
                // .client(new OkHttpClient()) // 自定义客户端，支持 OkHttp、Ribbon、Java 11 Http2
                .target(UserServiceFeignClient.class, "http://127.0.0.1:18080"); // 目标地址
    }

    @Bean
    public UserServiceFeignClient02 userServiceFeignClient02() {
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .contract(new SpringContract()) // 扩展使用 SpringMVC 契约
                .target(UserServiceFeignClient02.class, "http://127.0.0.1:18080"); // 目标地址
    }

}
