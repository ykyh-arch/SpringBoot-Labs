package cn.iocoder.springboot.lab32.activemqdemo.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * ActiveMQConfig 配置类
 *
 * @author Jaquez
 * @date 2021/12/16 10:30
 */
@Configuration
public class ActiveMQConfig {

    public static final String CLUSTERING_JMS_LISTENER_CONTAINER_FACTORY_BEAN_NAME = "clusteringJmsListenerContainerFactory";
    public static final String BROADCAST_JMS_LISTENER_CONTAINER_FACTORY_BEAN_NAME = "broadcastJmsListenerContainerFactory";

    public static final String CLUSTERING_JMS_TEMPLATE_BEAN_NAME = "clusteringJmsTemplate";
    public static final String BROADCAST_JMS_TEMPLATE_BEAN_NAME = "broadcastJmsTemplate";

    // ========== 集群消费 =========

    @Bean(CLUSTERING_JMS_LISTENER_CONTAINER_FACTORY_BEAN_NAME)
    public DefaultJmsListenerContainerFactory clusteringJmsListenerContainerFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(false);// 对应 spring.jms.pub-sub-domain 控制着是集群消费还是广播消费
        return factory;
    }

    @Bean(CLUSTERING_JMS_TEMPLATE_BEAN_NAME)
    public JmsMessagingTemplate clusteringJmsTemplate(ConnectionFactory connectionFactory) {
        // 创建 JmsTemplate 对象
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setPubSubDomain(false);
        // 创建 JmsMessageTemplate
        return new JmsMessagingTemplate(template);
    }

    // ========== 广播消费 ==========

    @Bean(BROADCAST_JMS_LISTENER_CONTAINER_FACTORY_BEAN_NAME)
    public DefaultJmsListenerContainerFactory broadcastJmsListenerContainerFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean(BROADCAST_JMS_TEMPLATE_BEAN_NAME)
    public JmsMessagingTemplate broadcastJmsTemplate(ConnectionFactory connectionFactory) {
        // 创建 JmsTemplate 对象
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setPubSubDomain(true);
        // 创建 JmsMessageTemplate
        return new JmsMessagingTemplate(template);
    }

}
