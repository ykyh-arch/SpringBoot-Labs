package cn.iocoder.springboot.lab86.demo.handler;

import cn.iocoder.springboot.lab86.demo.message.DemoMessageEvent;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * DemoMessageEventHandler 构造 EventHandler - 消费者
 *
 * @author jaquez
 * @date 2022/09/16 13:58
 **/
@Slf4j
public class DemoMessageEventHandler implements EventHandler<DemoMessageEvent>, WorkHandler<DemoMessageEvent> {
    @Override
    public void onEvent(DemoMessageEvent event, long sequence, boolean endOfBatch) throws Exception {
        try {
            // 这里停止1000ms是为了确定消费消息是异步的
            Thread.sleep(1000);
            log.info("[DemoMessageEventHandler#onEvent:{}]","消费者处理消息开始");
            if (event != null) {
                log.info("消费者消费的信息是：{}",event);
            }
        } catch (Exception e) {
            log.info("[DemoMessageEventHandler#onEvent:{}]","消费者处理消息失败");
        }
        log.info("[DemoMessageEventHandler#onEvent:{}]","消费者处理消息结束");
    }

    // WorkHandler 对应交给 WorkerPool 处理，参考自：https://www.cnblogs.com/kebibuluan/p/7655876.html
    @Override
    public void onEvent(DemoMessageEvent demoMessageEvent) throws Exception {
        log.info("[WorkHandler#onEvent：消费者消费的信息是：{}]",demoMessageEvent);
    }
}
