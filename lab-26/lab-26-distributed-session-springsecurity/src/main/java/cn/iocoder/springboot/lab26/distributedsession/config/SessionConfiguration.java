package cn.iocoder.springboot.lab26.distributedsession.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 采用默认的 Java 序列化方式
 */
@Configuration
@EnableRedisHttpSession // 自动化配置 Spring Session 使用 Redis 作为数据源
public class SessionConfiguration {

//    @Bean(name = "springSessionDefaultRedisSerializer")
//    public RedisSerializer springSessionDefaultRedisSerializer() {
//        // 默认采用GenericJackson2JsonRedisSerializer()序列化器
//        return RedisSerializer.json();
//    }

}
