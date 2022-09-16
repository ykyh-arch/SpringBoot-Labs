package cn.iocoder.springboot.lab86.demo.service.impl;

import cn.iocoder.springboot.lab86.demo.message.DemoMessageEvent;
import cn.iocoder.springboot.lab86.demo.service.DisruptorService;
import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * DisruptorServiceImpl
 *
 * @author jaquez
 * @date 2022/09/16 14:27
 **/
@Slf4j
@Service
public class DisruptorServiceImpl implements DisruptorService {

    @Autowired
    private RingBuffer<DemoMessageEvent> demoMessageEventRingBuffer;

    @Override
    public void send(String message) {

        log.info("接收到的内容: {}",message);
        // 获取下一个 Event 槽的下标
        long sequence = demoMessageEventRingBuffer.next();
        try {
            // 给 Event 填充数据
            DemoMessageEvent event = demoMessageEventRingBuffer.get(sequence);
            event.setMessage(message);
            event.setId(System.currentTimeMillis() / 1000);
            log.info("往消息队列中添加内容：{}", event);
        } catch (Exception e) {
            log.error("failed to add event to demoMessageEventRingBuffer for : e = {},{}",e,e.getMessage());
        } finally {
            // 发布 Event，激活观察者去消费，将 sequence 传递给该消费者
            // 注意最后的 publish 方法必须放在 finally 中以确保必须得到调用；如果某个请求的 sequence 未被提交将会堵塞后续的发布操作或者其他的 producer
            demoMessageEventRingBuffer.publish(sequence);
        }

    }
}
