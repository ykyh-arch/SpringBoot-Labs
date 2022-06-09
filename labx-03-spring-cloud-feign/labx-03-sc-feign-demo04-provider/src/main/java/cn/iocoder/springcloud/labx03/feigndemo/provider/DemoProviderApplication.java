package cn.iocoder.springcloud.labx03.feigndemo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/06/09 14:26
 */
@SpringBootApplication
public class DemoProviderApplication {

    public static void main(String[] args) {
        // SpringApplication.run(DemoProviderApplication.class, args);

        new SpringApplicationBuilder(DemoProviderApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

}
