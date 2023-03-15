package cn.iocoder.springboot.lab80.oss;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * 入口类
 * fastdfs 安装参考：https://hub.docker.com/r/qbanxiaoli/fastdfs
 * 案例参考：https://mp.weixin.qq.com/s/n_fxKVC3zc-6-WM6tNFECw,https://github.com/tobato/FastDFS_Client
 *
 * @author Jaquez
 * @date 2021/12/08 17:05
 */
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@SpringBootApplication
public class FileClientApplication {

//    public static void mainMaster(String[] args) {
//        SpringApplication.run(FileClientApplication.class, args);
//    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(FileClientApplication.class)
                .beanNameGenerator((beanDefinition, beanDefinitionRegistry) -> beanDefinition.getBeanClassName())
                .run(args);
    }

}
