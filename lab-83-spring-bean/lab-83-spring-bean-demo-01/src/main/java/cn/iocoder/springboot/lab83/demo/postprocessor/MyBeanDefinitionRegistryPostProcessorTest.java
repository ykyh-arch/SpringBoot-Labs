package cn.iocoder.springboot.lab83.demo.postprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * MyBeanDefinitionRegistryPostProcessorTest 测试类
 *
 * @author jaquez
 * @date 2022/01/07 16:13
 **/
public class MyBeanDefinitionRegistryPostProcessorTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        MyBeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor = new MyBeanDefinitionRegistryPostProcessor();
        applicationContext.addBeanFactoryPostProcessor(beanDefinitionRegistryPostProcessor);
        applicationContext.refresh();
        Person4 bean = applicationContext.getBean(Person4.class);
        System.out.println(bean);
    }

}
