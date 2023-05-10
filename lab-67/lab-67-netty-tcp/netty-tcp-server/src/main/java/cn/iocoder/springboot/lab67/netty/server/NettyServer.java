package cn.iocoder.springboot.lab67.netty.server;

import cn.iocoder.springboot.lab67.nettycore.utils.SpringUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * NettyServer 配置类
 *
 */
@Component
@Slf4j
public class NettyServer extends Thread {

    public static String MsgCode = "GBK";

    @Value("${server.netty.port}")
    public Integer port;

    @Override
    public void run() {
        startServer();
    }

    private void startServer() {
        EventLoopGroup bossGroup = null;
        EventLoopGroup workGroup = null;
        ServerBootstrap serverBootstrap = null;
        ChannelFuture future = null;
        try {
            // 初始化线程组
            bossGroup = new NioEventLoopGroup();
            workGroup = new NioEventLoopGroup();
            // 初始化服务端配置
            serverBootstrap = new ServerBootstrap();
            // 绑定线程组
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(SpringUtils.getBean(NettyServerHandler.class));

                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            future = serverBootstrap.bind(new InetSocketAddress(port)).sync();
            log.info(" *************TCP 服务端启动成功 Port：{}*********** ", port);
        } catch (Exception e) {
            log.error("TCP服务端启动异常", e);
        } finally {
            if (future != null) {
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    log.error("channel 关闭异常：", e);
                }
            }
            if (bossGroup != null) {
                // 线程组资源回收
                bossGroup.shutdownGracefully();
            }

            if (workGroup != null) {
                // 线程组资源回收
                workGroup.shutdownGracefully();
            }

        }
    }

}