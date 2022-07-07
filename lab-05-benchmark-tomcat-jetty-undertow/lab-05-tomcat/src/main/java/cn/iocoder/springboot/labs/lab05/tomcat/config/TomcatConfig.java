package cn.iocoder.springboot.labs.lab05.tomcat.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TomcatConfig 配置类
 *
 * @author jaquez
 * @date 2022/06/12 17:07
 **/
@Configuration
public class TomcatConfig {

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();
        tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
        tomcatFactory.setPort(8005); // 端口
        tomcatFactory.setContextPath("/tomcat-demo"); // 上下文路径
        return tomcatFactory;
    }
    class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
        @Override
        public void customize(Connector connector) {
            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            // 设置最大连接数，参考 Acceptor 类
            protocol.setMaxConnections(10000);
            // 设置最大线程数
            protocol.setMaxThreads(200);
            // 连接超时
            protocol.setConnectionTimeout(5000);
        }
    }

}
