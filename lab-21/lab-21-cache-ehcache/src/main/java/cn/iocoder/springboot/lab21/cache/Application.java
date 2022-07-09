package cn.iocoder.springboot.lab21.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * springboot 整合 ehcache 缓存示例，
 * ehcache 缓存特点：
 * 多级缓存、缓存数据有两种级别（内存、磁盘）、可以通过 RMI（远程方法调用，rpc 框架实现技术的一种）、可插入 API 等方式进行分布式缓存、
 * 支持多缓存管理器实例，以及一个实例的多个缓存区域、提供 Hibernate 的缓存实现等
 * 参考 ehcache 实现用户登陆失败 5 次锁定 5 分钟不能登陆：https://dominick-li.blog.csdn.net/article/details/81814709
 *
 * @author Jaquez
 * @date 2021/08/30 13:55
 */
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "cn.iocoder.springboot.lab21.cache.mapper")
public class Application {
}
