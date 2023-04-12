package cn.iocoder.springboot.lab37.loggingtlog.config;

import com.yomahub.tlog.httpclient.TLogHttpClientInterceptor;
import com.yomahub.tlog.hutoolhttp.TLogHutoolhttpInterceptor;
import com.yomahub.tlog.resttemplate.TLogRestTemplateInterceptor;
import org.apache.http.HttpRequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HttpClientConfig
 *
 * @author jaquez
 * @date 2023/04/12 13:59
 **/
@Configuration
public class HttpClientConfig {

    // ###################### 对 hutool-http 的支持 ####################
    @Bean
    @ConditionalOnClass({HttpRequestInterceptor.class})
    public TLogHutoolhttpInterceptor tLogHutoolhttpInterceptor(){
        return new TLogHutoolhttpInterceptor();
    }

    // ###################### 对 Httpclient 的支持 ####################
    @Bean
    public TLogHttpClientInterceptor tLogHttpClientInterceptor(){
        return new TLogHttpClientInterceptor();
    }

    // ###################### 对 RestTemplate 的支持 ####################
    @Bean
    public TLogRestTemplateInterceptor tLogRestTemplateInterceptor(){
        return new TLogRestTemplateInterceptor();
    }

}
