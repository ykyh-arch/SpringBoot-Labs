package cn.iocoder.springboot.lab21.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * spring cache 测试入门
 * @author Jaquez
 * @date 2021/08/30 11:26
 */
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "cn.iocoder.springboot.lab21.cache.mapper")
public class Application {
   // CacheAutoConfiguration
}
