package cn.iocoder.springboot.lab25.springwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WebSocket 和 HTTP 的关系，参考：https://www.iocoder.cn/Fight/Clarify-the-relationship-between-WebSocket-and-HTTP/?self
 * WebSocket和HTTP都是基于TCP协议的两个不同的协议，WebSocket依赖于HTTP连接，每个WebSocket连接都始于一个HTTP请求。
 * 在线调试工具：http://www.easyswoole.com/wstool.html
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
