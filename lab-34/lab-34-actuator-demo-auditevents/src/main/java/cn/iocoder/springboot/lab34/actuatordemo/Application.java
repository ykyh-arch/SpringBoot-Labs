package cn.iocoder.springboot.lab34.actuatordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * auditEvent 演示测试，{@link  InMemoryAuditEventRepository  }
 * @author Jaquez
 * @date 2021/09/22 14:26
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
