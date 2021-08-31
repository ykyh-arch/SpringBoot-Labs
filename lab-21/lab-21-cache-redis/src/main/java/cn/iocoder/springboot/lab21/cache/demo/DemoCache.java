package cn.iocoder.springboot.lab21.cache.demo;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 示例缓存
 *
 * @author jaquez
 * @date 2021/08/31 10:27
 **/
// 通过对 initialCacheConfigurations 进行自定义配置，可以实现对类、方法过期时间设置
@CacheConfig(cacheNames = "demoCache")
@Service
public class DemoCache {

    @Cacheable(keyGenerator = "demoKeyGenerator")
    public String demo(String str1,String str2){
        return UUID.randomUUID().toString();
    }

}

