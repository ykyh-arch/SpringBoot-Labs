package cn.iocoder.springboot.lab67.netty.client;

import cn.iocoder.springboot.lab67.nettycore.utils.SpringUtils;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import cn.iocoder.springboot.lab67.netty.client.handler.DemoClientHandler;
import cn.iocoder.springboot.lab67.netty.client.model.NettyMsgModel;
import cn.iocoder.springboot.lab67.netty.client.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * MessageProcessor 消息处理器，负责处理消息
 *
 */
@Slf4j
@Component
public class MessageProcessor {

    @Value("${netty.client.worker-thread}")
    private Integer workerThread;

    @Autowired
    private RedisCache redisCache;

    public static final String NETTY_QUEUE_LOCK = "nettyQueueLock:";

    private ExecutorService executor;

    @PostConstruct
    public void init() {
        this.executor = this.createDefaultExecutorService(10);
    }

    // 消息处理逻辑
    public void process(NettyMsgModel nettyMsgModel) {
        String imei = nettyMsgModel.getImei();
        try {
            synchronized (this) { // 为避免收到同一台设备多条消息后重复创建客户端，必须加锁
                if (redisCache.hasKey(NETTY_QUEUE_LOCK + imei)) { // 上一条消息处理中
                    log.info("imei={}消息处理中，重新入列", imei);
                    //放回队列重新等待消费，延迟 x 秒（实际项目中应该使用 rocketmq 或者 rabbitmq 实现延迟消费）
                    this.putDelay(nettyMsgModel);
                    log.info("imei={}消息处理中，重新入列完成", imei);
                    return;
                } else {
                    // 如果没有在连接中的直接加锁，保证一台设备只能创建一个客户端
                    redisCache.setCacheObject(NETTY_QUEUE_LOCK + imei, "1", 120, TimeUnit.SECONDS);
                }
            }
            // 缓存中存在则发送消息
            if (NettyClientHolder.get().containsKey(imei)) {
                NettyClient nettyClient = NettyClientHolder.get().get(imei);
                if (null != nettyClient.getChannelFuture() && nettyClient.getChannelFuture().channel().isActive()) { // 通道活跃直接发送消息
                    if (!nettyClient.getChannelFuture().channel().isWritable()) {
                        log.warn("警告，通道不可写，imei={}，channelId={}", nettyClient.getImei(),
                                nettyClient.getChannelFuture().channel().id());
                    }
                    nettyClient.send(nettyMsgModel.getMsg());
                } else {
                    log.info("client imei={}，通道不活跃，主动关闭", nettyClient.getImei());
                    nettyClient.close();
                    // 重新创建客户端发送
                    this.createClientAndSend(nettyMsgModel);
                }
            } else {  // 缓存中不存在则创建新的客户端
                this.createClientAndSend(nettyMsgModel);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 执行完后解锁
            redisCache.deleteObject(NETTY_QUEUE_LOCK + imei);
        }

    }

    // 创建 Netty 客户端
    private void createClientAndSend(NettyMsgModel nettyMsgModel) {
        log.info("创建客户端执行中，imei={}", nettyMsgModel.getImei());
        // 此处的 DemoClientHandler 可以根据自己的业务定义
        NettyClient nettyClient = SpringUtils.getBean(NettyClient.class, nettyMsgModel.getImei(), nettyMsgModel.getBizData(),
                this.createDefaultWorkGroup(this.workerThread), DemoClientHandler.class);
        executor.execute(nettyClient); // 执行客户端初始化
        try {
            // 利用锁等待客户端激活
            synchronized (nettyClient) {
                long c1 = System.currentTimeMillis();
                nettyClient.wait(5000); // 最多阻塞5秒，5秒后客户端仍然未激活则自动解锁
                long c2 = System.currentTimeMillis();
                log.info("创建客户端等待耗时={}ms", c2 - c1);
            }
            if (null != nettyClient.getChannelFuture() && nettyClient.getChannelFuture().channel().isActive()) { // 连接成功
                // 存入缓存
                NettyClientHolder.get().put(nettyMsgModel.getImei(), nettyClient);
                // 客户端激活后发送消息
                nettyClient.send(nettyMsgModel.getMsg());
            } else { // 连接失败
                log.warn("客户端创建失败，imei={}", nettyMsgModel.getImei());
                nettyClient.close();
                // 可以把消息重新入列处理，等待下次处理
                this.putDelay(nettyMsgModel);
            }
        } catch (Exception e) {
            log.error("客户端初始化发送消息异常===>{}", e.getMessage(), e);
        }
    }

    /**
     * 所有客户端共用的工作线程组，减少消耗服务器资源
     *
     * @param thread
     * @return
     */
    private EventLoopGroup createDefaultWorkGroup(int thread) {
        return new NioEventLoopGroup(thread, new ThreadFactoryImpl("NettyWorkGroupThread_", false));
    }

    /**
     * 客户端阻塞的线程池
     *
     * @param size 核心线程数，建议大于需要创建的客户端数量
     * @return
     */
    private ExecutorService createDefaultExecutorService(int size) {
        return new ThreadPoolExecutor(size, size * 2, 300L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1), new ThreadFactoryImpl("NettyClientThread_", false));
    }

    private void putDelay(NettyMsgModel nettyMsgModel) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                QueueHolder.get().offer(nettyMsgModel);
                timer.cancel();
            }
        }, 3000);
    }
}
