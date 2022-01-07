package cn.iocoder.springboot.lab83.demo.component;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 组件扫描测试类
 *
 * @author jaquez
 * @date 2022/01/07 13:53
 **/
@ComponentScan(basePackages = "cn.iocoder.springboot.lab83.demo.component")
public class ComponentScanTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComponentScanTest.class);
        Person2 person2 = applicationContext.getBean(Person2.class);

        System.out.println(person2);
    }
}
