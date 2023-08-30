package cn.iocoder.springboot.lab21.cache.demo;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * MainConfig1
 *
 * @author fw001
 * @date 2023/08/30 10:43
 **/
@EnableCaching
@ComponentScan
@Configuration
public class MainConfig1 {

    @Bean
    public CacheManager cacheManager() {
        // 创建缓存管理器(ConcurrentMapCacheManager：其内部使用ConcurrentMap实现的)，构造器用来指定缓存的名称，可以指定多个
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("cache1");
        return cacheManager;
    }

}
