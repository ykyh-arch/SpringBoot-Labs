package cn.iocoder.springboot.lab67.netty.client.controller;

import cn.iocoder.springboot.lab67.netty.client.QueueHolder;
import cn.iocoder.springboot.lab67.netty.client.model.NettyMsgModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试客户端消息
 *
 *
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    /**
     * 间隔发送两条消息
     */
    @GetMapping("testOne")
    public void testOne() {
        QueueHolder.get().offer(NettyMsgModel.create("87654321", "Hello World!"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        QueueHolder.get().offer(NettyMsgModel.create("87654321", "Hello World Too!"));
    }

    /**
     * 任意发送消息
     *
     * @param imei
     * @param msg
     */
    @GetMapping("testTwo")
    public void testTwo(@RequestParam String imei, @RequestParam String msg) {
        QueueHolder.get().offer(NettyMsgModel.create(imei, msg));
    }

    /**
     * 连续发送两条消息，第二条由于 redis 锁将会重新放回队列延迟消费。
     */
    @GetMapping("testThree")
    public void testThree() {
        QueueHolder.get().offer(NettyMsgModel.create("12345678", "Hello World!"));
        QueueHolder.get().offer(NettyMsgModel.create("12345678", "Hello World Too!"));
    }
}
