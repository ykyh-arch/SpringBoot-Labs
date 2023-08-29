package cn.iocoder.springboot.lab73.aopdemo.aspectj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author jaquez
 * @date 2023/07/10 11:30
 **/

@Configuration
@EnableAspectJAutoProxy // 这个可以启用通过 AspectJ 方式自动为符合条件的 bean 创建代理
public class MainConfig13 {

    //将 Aspect13 注册到 spring 容器
    @Bean
    public Aspect13 aspect13() {
        return new Aspect13();
    }

    @Bean
    public BeanService beanService1() {
        return new BeanService("beanService1");
    }

    @Bean
    public BeanService beanService2() {
        return new BeanService("beanService2");
    }
}
