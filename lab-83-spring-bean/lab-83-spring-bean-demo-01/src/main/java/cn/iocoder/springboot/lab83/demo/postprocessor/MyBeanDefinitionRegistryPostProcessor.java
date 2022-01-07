package cn.iocoder.springboot.lab83.demo.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * MyBeanDefinitionRegistryPostProcessor 自定义 BeanDefinitionRegistryPostProcessor
 *
 * 在 Spring 容器启动的时候会执行 BeanDefinitionRegistryPostProcessor 的 postProcessBeanDefinitionRegistry 方法，
 * 大概意思就是等 beanDefinition 加载完毕之后，对 beanDefinition 进行后置处理，可以在此进行调整 IOC 容器中的 beanDefinition，从而干扰到后面进行初始化 bean。
 *
 * @author jaquez
 * @date 2022/01/07 16:07
 **/
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Person4.class).getBeanDefinition();
        registry.registerBeanDefinition("person", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
