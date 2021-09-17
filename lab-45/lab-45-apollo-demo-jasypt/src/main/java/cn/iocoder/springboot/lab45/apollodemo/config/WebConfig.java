package cn.iocoder.springboot.lab45.apollodemo.config;

import cn.iocoder.springboot.lab45.apollodemo.listener.ApolloConfigChangeListener;
import cn.iocoder.springboot.lab45.apollodemo.listener.JaspytRefreshScopeRefreshedEventListener;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertySourceConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * apollo 跟 jasypt-spring-boot-xx.jar 不兼容处理，经测试貌似并没有解决问题，伤心
 * @author jaquez
 * @date 2021/09/17 17:27
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public JaspytRefreshScopeRefreshedEventListener jaspytRefreshScopeRefreshedEventListener(ConfigurableEnvironment environment, EncryptablePropertySourceConverter converter) {
        return new JaspytRefreshScopeRefreshedEventListener(environment,converter);
    }

    @Bean
    public ApolloConfigChangeListener apolloConfigChangeListener(JaspytRefreshScopeRefreshedEventListener listener) {
        return new ApolloConfigChangeListener(listener);
    }

}
