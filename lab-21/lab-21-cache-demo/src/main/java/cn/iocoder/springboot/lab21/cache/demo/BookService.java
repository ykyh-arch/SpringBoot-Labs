package cn.iocoder.springboot.lab21.cache.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * BookService
 *
 * @author fw001
 * @date 2023/08/30 17:41
 **/
@Component
public class BookService {

    @Cacheable(cacheNames = "cache1", key = "#root.targetClass.name+'-'+#root.method.name")
    public List<String> list() {
        System.out.println("---模拟从db中获取数据---");
        return Arrays.asList("java高并发", "springboot", "springcloud");
    }

}
