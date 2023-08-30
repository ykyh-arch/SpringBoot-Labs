package cn.iocoder.springboot.lab21.cache.demo;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * MainConfig2
 *
 * @author fw001
 * @date 2023/08/30 17:26
 **/
@ComponentScan
@EnableCaching
public class MainConfig2 {

    @Bean
    public CacheManager cacheManager() throws IOException {
        RedissonSpringCacheManager cacheManager = new RedissonSpringCacheManager(this.redissonClient());
        cacheManager.setCacheNames(Arrays.asList("cache1"));
        return cacheManager;
    }

    // @Bean
    public RedissonClient redissonClient() throws IOException {
        InputStream is = MainConfig2.class.getResourceAsStream("/cn/iocoder/springboot/lab21/cache/demo/redis.yml");
        Config config = Config.fromYAML(is);
        return Redisson.create(config);
    }
}
