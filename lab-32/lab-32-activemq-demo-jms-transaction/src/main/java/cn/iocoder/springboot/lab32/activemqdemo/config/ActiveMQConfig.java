package cn.iocoder.springboot.lab32.activemqdemo.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

/**
 * ActiveMQConfig 配置类
 *
 * @author Jaquez
 * @date 2021/12/16 10:30
 */
@Configuration
public class ActiveMQConfig{

    public static final String JMS_LISTENER_CONTAINER_FACTORY_BEAN_NAME = "selfJmsListenerContainerFactory";
    public static final String JMS_TEMPLATE_BEAN_NAME = "selfJmsTemplate";


    @Bean(JMS_LISTENER_CONTAINER_FACTORY_BEAN_NAME)
    public DefaultJmsListenerContainerFactory clusteringJmsListenerContainerFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        // factory.setPubSubDomain(false);// 对应 spring.jms.pub-sub-domain 控制着是集群消费还是广播消费
        factory.setSessionTransacted(true);// 开启事务
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        return factory;
    }

    @Bean(JMS_TEMPLATE_BEAN_NAME)
    public JmsMessagingTemplate clusteringJmsTemplate(ConnectionFactory connectionFactory) {
        // 创建 JmsTemplate 对象
        JmsTemplate template = new JmsTemplate(connectionFactory);
        // template.setPubSubDomain(false);
        template.setSessionTransacted(true);// 开启事务
        template.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        // 创建 JmsMessageTemplate
        return new JmsMessagingTemplate(template);
    }

}
