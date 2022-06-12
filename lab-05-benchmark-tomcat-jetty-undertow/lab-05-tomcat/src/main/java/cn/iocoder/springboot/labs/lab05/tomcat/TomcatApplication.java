package cn.iocoder.springboot.labs.lab05.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类，测试参数：java -jar lab-05-tomcat-1.0-SNAPSHOT.jar -Xms2g -Xmx2g -Xmn1g -XX:MaxMetaspaceSize=256m -Xss256k
 *
 * @author Jaquez
 * @date 2022/06/12 11:08
 */
@SpringBootApplication
public class TomcatApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomcatApplication.class);
    }

}
