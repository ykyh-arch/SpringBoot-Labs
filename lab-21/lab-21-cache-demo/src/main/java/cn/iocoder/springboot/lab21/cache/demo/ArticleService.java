package cn.iocoder.springboot.lab21.cache.demo;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * ArticleService
 *
 * @author fw001
 * @date 2023/08/30 10:39
 **/
@Component
@CacheConfig(cacheNames = "cache1")
public class ArticleService {

    @Cacheable(cacheNames = {"cache1"})
    public List<String> list() {
        System.out.println("获取文章列表!");
        return Arrays.asList("spring", "mysql", "java高并发", "maven");
    }

    @Cacheable(cacheNames = {"cache1"}, key = "#root.target.class.name+'-'+#page+'-'+#pageSize")
    public String getPage(int page, int pageSize) {
        String msg = String.format("page-%s-pageSize-%s", page, pageSize);
        System.out.println("从db中获取数据：" + msg);
        return msg;
    }

    @Cacheable(cacheNames = "cache1", key = "'getById'+#id", condition = "#cache")
    public String getById(Long id, boolean cache) {
        System.out.println("获取数据!");
        return "spring缓存:" + UUID.randomUUID().toString();
    }

    // 获取文章，先从缓存中获取，如果获取的结果为空，不要将结果放在缓存中
    Map<Long, String> articleMap = new HashMap<>();
    @Cacheable(cacheNames = "cache1", key = "'findById'+#id", unless = "#result==null")
    public String findById(Long id) {
        this.articleMap.put(1L, "spring系列");
        System.out.println("----获取文章:" + id);
        return articleMap.get(id);
    }

    @CachePut(cacheNames = "cache1", key = "'findById'+#id")
    public String add(Long id, String content) {
        System.out.println("新增文章:" + id);
        this.articleMap.put(id, content);
        return content;
    }

    @CacheEvict(cacheNames = "cache1", key = "'findById'+#id")
    public void delete(Long id) {
        System.out.println("删除文章：" + id);
        this.articleMap.remove(id);
    }

}
