package cn.iocoder.springboot.lab73.eldemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.stereotype.Component;

/**
 * SpelBeanFactoryPostProcessor
 *
 * @author fw001
 * @date 2023/08/29 17:56
 **/
@Component
public class SpelBeanFactoryPostProcessor  implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanExpressionResolver beanExpressionResolver = beanFactory.getBeanExpressionResolver();
        if (beanExpressionResolver instanceof StandardBeanExpressionResolver) {
            StandardBeanExpressionResolver resolver = (StandardBeanExpressionResolver) beanExpressionResolver;
            resolver.setExpressionPrefix("%{");
            resolver.setExpressionSuffix("}");
        }
    }

}
