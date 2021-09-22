package cn.iocoder.springboot.lab34.actuatordemo.config;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ActuateConfig 配置类
 * 为什么我们要配置一个 HttpTraceRepository 呢？HttpTraceEndpointAutoConfiguration 自动化配置类，创建 HttpTraceEndpoint 的前提是，需要有一个 HttpTraceRepository Bean
 * @author Jaquez
 * @date 2021/09/22 14:09
 */
@Configuration
public class ActuateConfig {

    @Bean
    public HttpTraceRepository httpTraceRepository() {
        // 默认，储最近的 100 条 HttpTrace 到内存中
        return new InMemoryHttpTraceRepository();
    }

}
