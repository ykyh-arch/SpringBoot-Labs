package cn.iocoder.springboot.labs.lab05.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类，测试参数：java -jar lab-05-jetty-apr-1.0-SNAPSHOT.jar -Xms2g -Xmx2g -Xmn1g -XX:MaxMetaspaceSize=256m -Xss256k
 *
 * @author Jaquez
 * @date 2022/06/12 13:48
 */
@SpringBootApplication
public class JettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JettyApplication.class);
    }

}
