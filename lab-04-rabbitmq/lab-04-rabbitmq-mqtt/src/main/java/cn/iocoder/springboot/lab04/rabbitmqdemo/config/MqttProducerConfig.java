package cn.iocoder.springboot.lab04.rabbitmqdemo.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * MqttProducerConfig
 *
 * @author jaquez
 * @date 2022/08/23 10:04
 **/
@Configuration
public class MqttProducerConfig {

    @Autowired
    private MqttConfig mqttConfig;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{mqttConfig.getServers()});
        options.setUserName(mqttConfig.getUsername());
        options.setPassword(mqttConfig.getPassword().toCharArray());
        options.setMaxInflight(10000);
        options.setCleanSession(true);
        factory.setConnectionOptions(options);

        // factory.setServerURIs(mqttConfig.getServers());
        // factory.setUserName(mqttConfig.getUsername());
        // factory.setPassword(mqttConfig.getPassword());
        return factory;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(mqttConfig.getServerClientId(), mqttClientFactory());

        messageHandler.setAsync(false); // 关闭异步模式发送消息时可能会阻塞。
        messageHandler.setAsyncEvents(false);
        messageHandler.setDefaultQos(2);
        messageHandler.setDefaultTopic(mqttConfig.getDefaultTopic());
        return messageHandler;
    }
}
