package cn.iocoder.springboot.labs.lab05.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.tools.jar.resources.jar;

/**
 * 启动类，测试参数：java -jar lab-05-tomcat-1.0-SNAPSHOT.jar -Xms2g -Xmx2g -Xmn1g -XX:MaxMetaspaceSize=256m -Xss256k
 *
 * 容器优化考虑点：线程数、超时时间、jvm 优化
 *
 * springboot 项目优化参考：https://mp.weixin.qq.com/s/TpcB9dsMv9Y6BL1lwANo4Q,https://mp.weixin.qq.com/s/RkaJD6RTHBPUzTx5HAT_NQ
 *
 * @author Jaquez
 * @date 2022/06/12 11:08
 */
@SpringBootApplication
public class TomcatApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomcatApplication.class);
    }

    /*远程调试，使用 IDEA 远程调试*/
    /**
     *
     * java -Djavax.net.debug=
     *     ssl -Xdebug -Xnoagent -Djava.compiler=
     *     NONE -Xrunjdwp:transport=
     *     dt_socket,server=y,suspend=
     *     n,address=8888 -
     *     jar springboot-1.0.jar
     *
     */
    /*远程调试，使用 jvm 工具远程调试*/
    /**
     *
     * java -jar -Djava.rmi.server.hostname=192.168.44.128 -
     * Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=911 -
     * Dcom.sun.management.jmxremote.ssl=false -
     * Dcom.sun.management.jmxremote.authenticate=false jantent-1.0-SNAPSHOT.jar
     *
     */

}
