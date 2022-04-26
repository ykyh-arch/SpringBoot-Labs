package cn.iocoder.springboot.lab83.demo.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置类
 *
 * 方式一：@Configuration + @Bean
 *
 * @author jaquez
 * @date 2022/01/07 11:54
 **/
@Configuration
public class MyConfiguration1 {

    @Bean
    public Person1 person() {
        Person1 person1 = new Person1();
        person1.setId(1L);
        person1.setName("person1");
        return person1;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfiguration1.class);
        Person1 bean = applicationContext.getBean(Person1.class);
        System.out.println(bean);
    }
}
