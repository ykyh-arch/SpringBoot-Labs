package cn.iocoder.springboot.lab67.nettyserverdemo.server.handler;

import cn.iocoder.springboot.lab67.nettycommondemo.codec.InvocationDecoder;
import cn.iocoder.springboot.lab67.nettycommondemo.codec.InvocationEncoder;
import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.MessageDispatcher;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Netty Server 初始化自定义事件处理逻辑
 *
 * @author Jaquez
 * @date 2022/01/26 17:48
 */
@Component
public class NettyServerHandlerInitializer extends ChannelInitializer<Channel> {

    /**
     * 心跳超时时间
     */
    private static final Integer READ_TIMEOUT_SECONDS = 3 * 60;

    @Autowired
    private MessageDispatcher messageDispatcher;
    @Autowired
    private NettyServerHandler nettyServerHandler;

    // 初始化 Channel
    @Override
    protected void initChannel(Channel ch) {
        // 获得 Channel 对应的 ChannelPipeline
        ChannelPipeline channelPipeline = ch.pipeline();
        // 添加一堆 NettyServerHandler 到 ChannelPipeline 中，开始通道流水线装配，负责数据读写、处理业务逻辑的 handler。
        channelPipeline
                // 空闲检测，实现服务端发现 180 秒未从客户端读取到消息，主动断开连接。
                .addLast(new ReadTimeoutHandler(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS))
                // 编码器
                .addLast(new InvocationEncoder())  // new ProtobufDecoder()
                // 解码器
                .addLast(new InvocationDecoder())  // new ProtobufDecoder()
                // 消息分发器
                .addLast(messageDispatcher)
                // 服务端处理器
                .addLast(nettyServerHandler)
        ;
    }

}
