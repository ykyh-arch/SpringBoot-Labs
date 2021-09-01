package cn.iocoder.springboot.lab78.lock;

import cn.iocoder.springboot.lab78.lock.service.ICacheKeyGenerator;
import cn.iocoder.springboot.lab78.lock.service.impl.LockKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * 分布式锁测试入门（处理重复提交问题）
 * 利用自定义注解、Spring Aop、Redis Cache实现表单防重复提交
 * @author Jaquez
 * @date 2021/08/31 15:28
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ICacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGenerator();
    }

    // 对 key value 进行序列化处理
    @Bean
    public RedisTemplate<String, Serializable> limitRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
