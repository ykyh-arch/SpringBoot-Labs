package cn.iocoder.springboot.lab17.dynamicdatasource.transaction;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类，基于 jdbc 的实现方式，参考：https://blog.didispace.com/spring-boot-learning-24-3-12/
 *
 * @author Jaquez
 * @date 2022/05/27 11:37
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)  // 开启 CGLIB 代理
public class Application {

}