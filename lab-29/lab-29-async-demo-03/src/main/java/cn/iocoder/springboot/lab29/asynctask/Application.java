package cn.iocoder.springboot.lab29.asynctask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 引入线程池 + Queue 缓冲队列实现高并发业务场景，参考：https://mp.weixin.qq.com/s/Qsvj5hIJZUIAF87_D3yVFQ
 * 多线程使用参考：https://mp.weixin.qq.com/s/-U8Hpv0Xmlj66gZy7y40Kg
 *
 * @author jaquez
 * @date 2021/11/04 18:16
 **/
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
