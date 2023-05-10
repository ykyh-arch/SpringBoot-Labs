package cn.iocoder.springboot.lab67.netty;

import cn.iocoder.springboot.lab67.nettycore.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import cn.iocoder.springboot.lab67.netty.client.MessageProcessor;
import cn.iocoder.springboot.lab67.netty.client.QueueHolder;
import cn.iocoder.springboot.lab67.netty.client.ThreadFactoryImpl;
import cn.iocoder.springboot.lab67.netty.client.model.NettyMsgModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 客户端启动类
 * 监听消息队列是否有消息，有消息交给 MessageProcessor 处理！<br/>
 * 参考自：https://mp.weixin.qq.com/s/Q6yHjLiZg-BP_WKDXA0Gng
 *
 */
@Slf4j
@Configuration
@SpringBootApplication
public class NettyClientApplication implements CommandLineRunner {

    @Autowired
    private MessageProcessor messageProcessor;

    @Bean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

    private static final Integer MAIN_THREAD_POOL_SIZE = 4;

    public static void main(String[] args) {
        SpringApplication.run(NettyClientApplication.class, args);
    }

    private final ExecutorService executor = Executors.newFixedThreadPool(MAIN_THREAD_POOL_SIZE,
            new ThreadFactoryImpl("Loop_Thread_", false));

    @Override
    public void run(String... args) throws Exception {
        Thread loopThread = new Thread(new LoopThread());
        loopThread.start();
    }

    // 监听 BlockingQueue 消息并处理
    public class LoopThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < MAIN_THREAD_POOL_SIZE; i++) {
                executor.execute(() -> {
                    while (true) {
                        // 取走 BlockingQueue 里排在首位的对象，若 BlockingQueue 为空，阻断进入等待状态直到获取到消息
                        try {
                            NettyMsgModel nettyMsgModel = QueueHolder.get().take(); // 使用 take 方法会使该线程一直阻塞直到队列收到消息后进入下一次循环。
                            messageProcessor.process(nettyMsgModel);
                        } catch (InterruptedException e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                });
            }
        }
    }
}