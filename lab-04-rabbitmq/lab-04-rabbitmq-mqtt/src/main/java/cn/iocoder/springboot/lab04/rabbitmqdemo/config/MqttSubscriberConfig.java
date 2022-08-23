package cn.iocoder.springboot.lab04.rabbitmqdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * MqttSubscriberConfig
 *
 * @author jaquez
 * @date 2022/08/23 10:36
 **/
@Configuration
public class MqttSubscriberConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MqttConfig mqttConfig;

    // @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs(mqttConfig.getServers());
        factory.setUserName(mqttConfig.getUsername());
        factory.setPassword(mqttConfig.getPassword());
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(mqttConfig.getClientId(),
               mqttClientFactory(), mqttConfig.getDefaultTopic());

        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2); // 消息质量
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handlerTest() {

        return message -> {
            try {
                String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
                String messageStr = message.getPayload().toString();
                System.out.println("接收到消息：" + messageStr);
                logger.info("topic:{},handleMessage : {}", topic, message.getPayload());
            } catch (MessagingException ex) {
                // logger.info(ex.getMessage());
            }
        };
    }

}
