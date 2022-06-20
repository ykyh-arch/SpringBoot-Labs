package cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo;

import cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo.message.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/06/17 15:30
 */
@SpringBootApplication
@EnableBinding(MySource.class) // 开启扫描其 @Input 和 @Output 注解。
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
