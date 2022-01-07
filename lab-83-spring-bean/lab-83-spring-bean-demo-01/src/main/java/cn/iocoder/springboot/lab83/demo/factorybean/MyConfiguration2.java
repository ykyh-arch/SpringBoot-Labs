package cn.iocoder.springboot.lab83.demo.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置类，将 PersonFactoryBean 加入到容器中
 *
 * @author jaquez
 * @date 2022/01/07 15:06
 **/
@Configuration
public class MyConfiguration2 {

    @Bean
    public PersonFactoryBean personFactoryBean() {
        return new PersonFactoryBean();
    }

    // 测试
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfiguration2.class);
        Person4 bean = applicationContext.getBean(Person4.class);
        System.out.println(bean);
    }

}
