package cn.iocoder.springboot.lab83.demo;

import cn.iocoder.springboot.lab83.demo.config.Person1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 将 bean 放入 spring 容器的几种方式演示，参考自：https://mp.weixin.qq.com/s/f1JMiNdZrrZvZra6fLiUMA
 * <p>
 * 向 spring 容器中加入 bean 的几种方式.
 * @Configuration + @Bean
 * @ComponentScan + @Component
 * @Import 配合接口进行导入
 * <p>
 * 使用FactoryBean。
 * <p>
 * 实现 BeanDefinitionRegistryPostProcessor 进行后置处理。
 *
 * @author Jaquez
 * @date 2022/01/07 11:51
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext cac = SpringApplication.run(DemoApplication.class, args);
        // 方式一：测试
        Person1 bean = cac.getBean(Person1.class);
        System.out.println(bean);
    }
}
