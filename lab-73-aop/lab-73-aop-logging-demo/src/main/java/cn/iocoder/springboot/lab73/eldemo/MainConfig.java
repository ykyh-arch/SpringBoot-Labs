package cn.iocoder.springboot.lab73.eldemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * MainConfig
 *
 * @author fw001
 * @date 2023/08/29 18:13
 **/
@ComponentScan
@Configuration
public class MainConfig {

    @Bean
    public String name() {
        return "路粉";
    }

    @Bean
    public String msg() {
        return "欢迎和我一起学习java各种技术！";
    }
}
