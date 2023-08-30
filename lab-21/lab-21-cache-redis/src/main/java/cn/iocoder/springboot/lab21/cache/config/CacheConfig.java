package cn.iocoder.springboot.lab21.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 缓存配置类
 * @Cacheable 注解不支持配置过期时间，所有需要通过配置 CacheManager 来配置默认的过期时间和针对每个类或者是方法进行缓存失效时间配置。
 * @author jaquez
 * @date 2021/08/31 09:56
 **/
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                // 默认策略，未配置的 key 会使用这个，可以自定义缓存过期策略
                this.getRedisCacheConfigurationWithTtl(30*60),
                // 指定 key 策略
                this.getRedisCacheConfigurationMap()
        );
    }

    // 初始化 redis 缓存配置，自定义参数设置
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        //demoCache 和 BasicDataCache 进行过期时间配置（24小时、三十分钟）
        redisCacheConfigurationMap.put("demoCache", this.getRedisCacheConfigurationWithTtl(24*60*60));
        redisCacheConfigurationMap.put("BasicDataCache", this.getRedisCacheConfigurationWithTtl(30*60));
        return redisCacheConfigurationMap;
    }

    // 默认的 redis 缓存配置
    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {

        // 使用 JSON 序列化方式（库是 Jackson ）。
         Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
         ObjectMapper om = new ObjectMapper();
         om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
         om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
         jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer)
                        // .fromSerializer(RedisSerializer.json())
        ).entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }

    // 自定义 key 生成器
    @Bean
    public KeyGenerator demoKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("." + method.getName());
                if(params==null||params.length==0||params[0]==null){
                    return null;
                }
                String join = String.join("&", Arrays.stream(params).map(Object::toString).collect(Collectors.toList()));
                String format = String.format("%s{%s}", sb.toString(), join);
                System.out.println(String.format("缓存key：%s", format));
                return format;
            }
        };
    }

}
