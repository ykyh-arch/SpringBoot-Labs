package cn.iocoder.springboot.lab34.actuatordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.mappings.MappingDescriptionProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Metrics 指标测试，指标收集器( Metrics Collector ) 是基于 Micrometer 来实现的。
 * mappings 指标测试，{@link MappingDescriptionProvider}
 * @author Jaquez
 * @date 2021/09/22 13:54
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
