package cn.iocoder.springboot.lab67.netty.client;

import java.util.concurrent.ConcurrentHashMap;

/**
 * NettyClientHolder Netty 客户端上下文环境
 *
 */
public class NettyClientHolder {

    private static final ConcurrentHashMap<String, NettyClient> clientMap = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, NettyClient> get() {
        return clientMap;
    }

}
