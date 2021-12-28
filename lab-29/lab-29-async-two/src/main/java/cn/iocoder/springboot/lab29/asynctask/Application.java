package cn.iocoder.springboot.lab29.asynctask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;

/**
 * 自定义执行器演示示例，参考：https://mp.weixin.qq.com/s/7M9eZUNcXLm6767FVvnb2w
 *
 * @author Jaquez
 * @date 2021/12/28 14:42
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
