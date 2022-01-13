package cn.iocoder.springboot.lab60;

import cn.iocoder.springboot.lab60.common.CustomShenyuResult;
import org.apache.shenyu.plugin.api.result.ShenyuResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Shenyu 测试 springboot 应用，参考自：https://github.com/apache/incubator-shenyu/tree/master/shenyu-examples/shenyu-examples-http
 *
 * @author Jaquez
 * @date 2022/01/12 14:08
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ShenyuResult customShenyuResult() {
        return new CustomShenyuResult();
    }

}
