package cn.iocoder.springboot.lab67.netty.client;

import cn.iocoder.springboot.lab67.netty.client.model.NettyMsgModel;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 本项目为演示使用本地队列，实际生产中应该使用消息中间件代替（rocketmq 或 rabbitmq）
 *
 */
public class QueueHolder {

    private static final ArrayBlockingQueue<NettyMsgModel> queue = new ArrayBlockingQueue<>(100);

    public static ArrayBlockingQueue<NettyMsgModel> get() {
        return queue;
    }
}
