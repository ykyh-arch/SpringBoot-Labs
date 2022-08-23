package cn.iocoder.springboot.lab04.rabbitmqdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.stereotype.Component;

/**
 * MqttConfig
 *
 * @author jaquez
 * @date 2022/08/23 10:07
 **/
@Component
@IntegrationComponentScan
@ConfigurationProperties(prefix = "demo.mqtt")
public class MqttConfig {

    /**
     * 服务地址
     */
    private String servers;

    /**
     * 账户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 服务端id
     */
    private String serverClientId;

    /**
     * 默认主题
     */
    private String defaultTopic;

    public String getServers() {
        return servers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getServerClientId() {
        return serverClientId;
    }

    public void setServerClientId(String serverClientId) {
        this.serverClientId = serverClientId;
    }

    public String getDefaultTopic() {
        return defaultTopic;
    }

    public void setDefaultTopic(String defaultTopic) {
        this.defaultTopic = defaultTopic;
    }
}
