package cn.iocoder.springboot.lab22.validation.config;

import cn.iocoder.springboot.lab22.validation.validator.PersonDtoValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc 全局配置类
 *
 * @author jaquez
 * @date 2021/09/15 15:41
 **/
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加全局校验器
     */
    @Override
    public Validator getValidator() {
        return new PersonDtoValidator();
    }

}
