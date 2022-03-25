package cn.iocoder.springcloudnetflix.labx02.ribbondemo.consumer.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbon.DefaultRibbonClientConfiguration;
import ribbon.UserProviderRibbonClientConfiguration;

/**
 * 负载均衡配置文件
 *
 * @author Jaquez
 * @date 2022/03/25 10:48
 */
@Configuration
@RibbonClients(
        value = {
                @RibbonClient(name = "demo-provider", configuration = UserProviderRibbonClientConfiguration.class) // 客户端级别的配置
        },
        defaultConfiguration = DefaultRibbonClientConfiguration.class // 全局配置
)
public class RibbonConfiguration {
}
