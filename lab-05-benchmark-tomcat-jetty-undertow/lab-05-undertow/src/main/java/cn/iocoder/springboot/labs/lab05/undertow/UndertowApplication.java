package cn.iocoder.springboot.labs.lab05.undertow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类，测试参数：java -jar lab-05-undertow-1.0.0.jar -Xms2g -Xmx2g -Xmn1g -XX:MaxMetaspaceSize=256m -Xss256k
 *
 * @author Jaquez
 * @date 2022/06/12 13:51
 */
@SpringBootApplication
public class UndertowApplication {

    public static void main(String[] args) {
        SpringApplication.run(UndertowApplication.class);
    }

}
