package cn.iocoder.springboot.lab21.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Demo 测试
 *
 * @author jaquez
 * @date 2021/08/31 10:34
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DemoTest {

    private static final String CACHE_NAME_DEMO = "demoCache";

    @Autowired
    private cn.iocoder.springboot.lab21.cache.demo.DemoCache demoCache;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCacheManager() {
        System.out.println(cacheManager); // org.springframework.data.redis.cache.RedisCacheManager@322e49ee
    }

    @Test
    public void testDemo() {
        String result = demoCache.demo("sunkukong","douzhanshenfo");
        System.out.println("result：" + result);
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_DEMO).get("cn.iocoder.springboot.lab21.cache.demo.DemoCache.demo{sunkukong&douzhanshenfo}", String.class));
    }

}
