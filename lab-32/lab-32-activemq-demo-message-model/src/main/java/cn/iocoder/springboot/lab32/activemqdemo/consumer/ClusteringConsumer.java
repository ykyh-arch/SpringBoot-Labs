package cn.iocoder.springboot.lab32.activemqdemo.consumer;

import cn.iocoder.springboot.lab32.activemqdemo.config.ActiveMQConfig;
import cn.iocoder.springboot.lab32.activemqdemo.message.ClusteringMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 集群消费消费者消费消息示例
 *
 * @author Jaquez
 * @date 2021/12/16 10:45
 */
@Component
public class ClusteringConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 指定集群消费模式
    @JmsListener(destination = ClusteringMessage.QUEUE,
            containerFactory = ActiveMQConfig.CLUSTERING_JMS_LISTENER_CONTAINER_FACTORY_BEAN_NAME)
    public void onMessage(ClusteringMessage message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
