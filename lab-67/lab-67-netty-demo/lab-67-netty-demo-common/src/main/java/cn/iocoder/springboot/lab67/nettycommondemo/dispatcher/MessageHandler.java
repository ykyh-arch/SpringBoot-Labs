package cn.iocoder.springboot.lab67.nettycommondemo.dispatcher;

import io.netty.channel.Channel;

/**
 * 消息处理器接口
 *
 * @author Jaquez
 * @date 2022/01/28 15:38
 */
public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     *
     * @param channel 通道
     * @param message 消息
     */
    void execute(Channel channel, T message);

    /**
     * @return 消息类型，即每个 Message 实现类上的 TYPE 静态字段
     */
    String getType();

}
