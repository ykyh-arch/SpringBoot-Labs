package cn.iocoder.springboot.lab67.nettyserverdemo.config;

import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.MessageDispatcher;
import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.MessageHandlerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * NettyServerConfig 配置类，将 MessageDispatcher 和 MessageHandlerContainer 放入 IOC 容器里
 *
 * @author Jaquez
 * @date 2022/01/27 11:20
 */
@Configuration
public class NettyServerConfig {

    @Bean
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher();
    }

    @Bean
    public MessageHandlerContainer messageHandlerContainer() {
        return new MessageHandlerContainer();
    }

}
