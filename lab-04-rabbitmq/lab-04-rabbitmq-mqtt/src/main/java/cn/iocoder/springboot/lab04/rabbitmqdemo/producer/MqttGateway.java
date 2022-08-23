package cn.iocoder.springboot.lab04.rabbitmqdemo.producer;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Mqtt 协议网关接口
 *
 * @author Jaquez
 * @date 2022/08/23 10:35
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    // 向默认的 topic 发送消息
    void sendMessage2Mqtt(String payload);
    // 向指定的 topic 发送消息
    void sendMessage2Mqtt(String payload,@Header(MqttHeaders.TOPIC) String topic);
    // 向指定的 topic 发送消息，并指定服务质量参数
    void sendMessage2Mqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}
