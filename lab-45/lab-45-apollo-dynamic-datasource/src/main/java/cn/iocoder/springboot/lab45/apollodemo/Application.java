package cn.iocoder.springboot.lab45.apollodemo;

import cn.iocoder.springboot.lab45.apollodemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Apollo 配置动态数据源演示，参考：https://github.com/ctripcorp/apollo-use-cases/tree/master/dynamic-datasource
 * 思路：spring已经有个抽象类{@link AbstractRoutingDataSource }很好的实现了。通过AbstractRoutingDataSource对DataSource的管理，使用apollo配置动态推送能力，动态修改AbstractRoutingDataSource中resolvedDataSources数据源实例，可以很好的实现动态变更线上数据源。
 *
 * @author Jaquez
 * @date 2021/09/18 10:19
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 排除自动装配
public class Application {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        // 查看 Environment
        Environment environment = context.getEnvironment();
        System.out.println(environment);
    }

    @Component
    public class UserCommandLineRunner implements CommandLineRunner {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        @Autowired
        private UserRepository userRepository;

        @Override
        public void run(String... args) {
            Executors.newSingleThreadExecutor().submit(() -> {
                while (true) {
                    try {
                        logger.info("user name:" + userRepository.findById(1).get().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

    }

}
