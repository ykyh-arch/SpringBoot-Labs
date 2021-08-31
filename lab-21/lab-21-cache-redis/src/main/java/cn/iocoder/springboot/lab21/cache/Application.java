package cn.iocoder.springboot.lab21.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * spring 缓存，整合 redis 示例
 * @author Jaquez
 * @date 2021/08/31 09:54
 */
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "cn.iocoder.springboot.lab21.cache.mapper")
public class Application {
}
