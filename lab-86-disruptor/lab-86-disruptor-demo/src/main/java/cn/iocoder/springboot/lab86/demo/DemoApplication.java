package cn.iocoder.springboot.lab86.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 参考：https://mp.weixin.qq.com/s/EczAna4q1DyeiHDPMkdYJw，一生产者多消费者参考：https://blog.csdn.net/qq_19558705/article/details/77247912
 *
 * @author Jaquez
 * @date 2022/04/25 19:17
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
    }
}
