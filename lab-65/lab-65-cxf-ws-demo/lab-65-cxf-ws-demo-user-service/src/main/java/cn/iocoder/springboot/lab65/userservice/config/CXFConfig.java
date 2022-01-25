package cn.iocoder.springboot.lab65.userservice.config;

import cn.iocoder.springboot.lab65.userservice.service.UserService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * CXFConfig 配置类
 *
 * @author Jaquez
 * @date 2022/01/25 15:41
 */
@Configuration
public class CXFConfig {

    public static final String NAMESPACE_URI = "https://github.com/ykyh-arch/SpringBoot-Labs/tree/Jaquez/lab-65/lab-65-cxf-ws-demo";

    // 用于 Web Services 服务的发布。
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint userServiceEndpoint(UserService userService) {
        Endpoint endpoint = Endpoint.create(userService);
        endpoint.publish("/user");//发布地址
        return endpoint;
    }

}
