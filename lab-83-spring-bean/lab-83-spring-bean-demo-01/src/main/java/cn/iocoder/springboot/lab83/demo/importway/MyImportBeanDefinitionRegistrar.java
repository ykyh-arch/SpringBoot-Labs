package cn.iocoder.springboot.lab83.demo.importway;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * MyImportBeanDefinitionRegistrar 导入自定义的 BeanDefinitionRegistrar
 *
 * @author jaquez
 * @date 2022/01/07 14:27
 **/
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 构建一个 beanDefinition，BeanDefinition 可以简单理解为 bean 的定义（bean的元数据），也是需要放在 IOC 容器中进行管理的，先有 bean 的元数据，applicationContext 再根据 bean 的元数据去创建 Bean。
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Person3.class).getBeanDefinition();
        // 将 beanDefinition 注册到Ioc容器中.
        registry.registerBeanDefinition("person3", beanDefinition);
    }

}
