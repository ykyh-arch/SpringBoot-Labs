package cn.iocoder.springboot.lab25.springwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 参考：https://www.xncoding.com/2017/07/15/spring/sb-websocket.html
 * STOMP：即Simple Text Orientated Messaging Protocol，它是一个简单的文本消息传输协议，属于 WebSocket 的子协议，
 * 提供了一个可互操作的连接格式，允许STOMP客户端与任意STOMP消息代理（Broker）进行交互。
 * @author Jaquez
 * @date 2021/07/26 18:32
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
