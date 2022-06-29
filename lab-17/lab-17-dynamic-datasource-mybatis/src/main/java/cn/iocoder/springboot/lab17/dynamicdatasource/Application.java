package cn.iocoder.springboot.lab17.dynamicdatasource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * mybatis + aop + mysql 读写分离示例，参考：https://mp.weixin.qq.com/s/0YScuegIiFDmh_J-A3iQmQ
 *
 * @author Jaquez
 * @date 2022/06/29 16:39
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true) // http://www.voidcn.com/article/p-zddcuyii-bpt.html
public class Application {
}
