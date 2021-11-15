package cn.iocoder.springcloud.labx23.demo.config;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 配置文件
 * @author Jaquez
 * @date 2021/11/15 14:44
 */
@Configuration
@EnableAspectJAutoProxy // 开启 AOP 代理的支持
public class HystrixConfig {

    @Bean
    public HystrixCommandAspect hystrixCommandAspect() {
        return new HystrixCommandAspect();
    }

}
