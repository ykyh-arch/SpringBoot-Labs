package cn.iocoder.springboot.lab22.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
// 目的 使得Spring AOP 能将当前代理对象设置到 AopContext 中。
@EnableAspectJAutoProxy(exposeProxy = true) // http://www.voidcn.com/article/p-zddcuyii-bpt.html
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
