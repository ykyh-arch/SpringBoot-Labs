package cn.iocoder.springboot.lab86.demo.config;

import cn.iocoder.springboot.lab86.demo.factory.MessageEventFactory;
import cn.iocoder.springboot.lab86.demo.handler.DemoMessageEventHandler;
import cn.iocoder.springboot.lab86.demo.message.DemoMessageEvent;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 队列方案选择方式：ArrayBlcokingQueue、LinkedBlockingQueue、Disruptor，参考：https://www.cnblogs.com/JaxYoun/p/14088301.html
 * DisruptorConfiguration 经过测试，Disruptor 的速度比 LinkedBlockingQueue 提高了七倍。所以，当你在使用 LinkedBlockingQueue 出现性能瓶颈的时候，你就可以考虑采用 Disruptor 的代替。
 *
 * @author jaquez
 * @date 2022/09/16 14:10
 **/
@Configuration
public class DisruptorConfiguration {

    @Bean("demoMessageEventRingBuffer")
    public RingBuffer<DemoMessageEvent> demoMessageEventRingBuffer() {
        // 定义用于事件处理的线程池， Disruptor 通过 java.util.concurrent.ExecutorSerivce 提供的线程来触发 consumer 的事件处理
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 指定事件工厂
        MessageEventFactory factory = new MessageEventFactory();

        // 指定 ringbuffer 字节大小，必须为2的N次方（能将求模运算转为位运算提高效率），否则将影响效率
        int bufferSize = 1024 * 256;

        // 使用 disruptor 实现方式，与 WorkerPool 实现方式二选一
        // 单线程模式，获取额外的性能
        Disruptor<DemoMessageEvent> disruptor = new Disruptor<>(factory, bufferSize, executor,
                ProducerType.SINGLE, new BlockingWaitStrategy());

        // 设置事件业务处理器 --- 消费者
        disruptor.handleEventsWith(new DemoMessageEventHandler());
        // disruptor.handleEventsWithWorkerPool(new DemoMessageEventHandler());

        // 启动 disruptor 线程
        disruptor.start();

        // 获取 ringbuffer 环，用于接取生产者生产的事件
        RingBuffer<DemoMessageEvent> ringBuffer = disruptor.getRingBuffer();

        // 使用 WorkerPool 消息处理器实现方式

        // RingBuffer ringBuffer = RingBuffer.createSingleProducer(factory, bufferSize);

        // SequenceBarrier sequenceBarrier=ringBuffer.newBarrier();

        // WorkerPool workerPool = new WorkerPool(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), new DemoMessageEventHandler());

        // workerPool.start(executor);

        return ringBuffer;
    }
}
