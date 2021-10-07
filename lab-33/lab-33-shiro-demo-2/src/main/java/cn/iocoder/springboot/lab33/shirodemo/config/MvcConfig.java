package cn.iocoder.springboot.lab33.shirodemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MvcConfig 配置类
 *
 * @author jaquez
 * @date 2021/10/07 12:09
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        // fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastJsonHttpMessageConverter);
//    }

}
