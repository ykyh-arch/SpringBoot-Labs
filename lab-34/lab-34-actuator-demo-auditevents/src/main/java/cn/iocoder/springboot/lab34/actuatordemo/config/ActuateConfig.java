package cn.iocoder.springboot.lab34.actuatordemo.config;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AuditEventRepository 配置类
 * 什么我们要配置一个 AuditEventRepository 呢？AuditEventsEndpointAutoConfiguration 自动化配置类，创建 AuditEventsEndpoint 的前提是，需要有一个 AuditEventRepository Bean 。
 * @author Jaquez
 * @date 2021/09/22 14:32
 */
@Configuration
public class ActuateConfig {

    @Bean
    public AuditEventRepository auditEventRepository() {
        // 默认，存储最近的 1000 条 AuditEvents 到内存中。
        return new InMemoryAuditEventRepository();
    }

}
