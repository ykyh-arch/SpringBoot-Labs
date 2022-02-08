package cn.iocoder.springboot.lab67.nettycommondemo.dispatcher;

import cn.iocoder.springboot.lab67.nettycommondemo.codec.Invocation;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MessageDispatcher 消息分发器，将 Invocation 分发到其对应的 MessageHandler 中，进行业务逻辑的执行。
 *
 * @author Jaquez
 * @date 2022/01/28 16:28
 */
@ChannelHandler.Sharable // 多个 channel 可以共享
public class MessageDispatcher extends SimpleChannelInboundHandler<Invocation> { // Inbound 处理网络请求内容

    @Autowired
    private MessageHandlerContainer messageHandlerContainer;

    private final ExecutorService executor =  Executors.newFixedThreadPool(200);

    // 处理消息，进行分发。
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation invocation) {
        // 获得 type 对应的 MessageHandler 处理器
        MessageHandler messageHandler = messageHandlerContainer.getMessageHandler(invocation.getType());
        // 获得  MessageHandler 处理器 的消息类
        Class<? extends Message> messageClass = MessageHandlerContainer.getMessageClass(messageHandler);
        // 解析消息
        Message message = JSON.parseObject(invocation.getMessage(), messageClass);
        // 执行逻辑
        executor.submit(new Runnable() {

            @Override
            public void run() {
                // noinspection unchecked
                messageHandler.execute(ctx.channel(), message);
            }

        });
    }

}
