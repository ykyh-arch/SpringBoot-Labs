package cn.iocoder.springboot.lab26.distributedsession.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.AbstractMongoSessionConverter;
import org.springframework.session.data.mongo.JacksonMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

/**
 * @EnableMongoHttpSession 属性说明
 * maxInactiveIntervalInSeconds 属性，Session 不活跃后的过期时间，默认为 1800 秒。
 * collectionName 属性，在 MongoDB 中，存储 Session 的集合名，默认为 "sessions"。
 * @author Jaquez
 * @date 2021/07/16 10:16
 */
@Configuration
@EnableMongoHttpSession // 自动化配置 Spring Session 使用 MongoDB 作为数据源，外部存储器
public class SessionConfiguration {

    @Bean
    public AbstractMongoSessionConverter mongoSessionConverter() {
        // 采用Json序列化方式，默认采用JDK序列化方式，可读性差
        return new JacksonMongoSessionConverter();
    }

}
