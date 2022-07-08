package cn.iocoder.springboot.lab77.ratelimiter.controller;

import cn.iocoder.springboot.lab77.ratelimiter.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SemaphoreTestControllerTest
 *
 * @author jaquez
 * @date 2022/07/08 17:03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class SemaphoreTestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void batchTestRatelimit() throws Exception {
        // 设置线程池最大执行 20 个线程并发执行任务，AtomicInteger 通过 CAS 操作能保证统计数量的原子性
        int threadSize = 20;
        AtomicInteger successCount = new AtomicInteger(0);
        CountDownLatch downLatch = new CountDownLatch(20);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadSize);

        for (int i = 0; i < threadSize; i++) {
            fixedThreadPool.submit(() -> {

                ResultActions resultActions = null;
                try {
                    resultActions = mvc.perform(MockMvcRequestBuilders.get("/semaphoreLimit"));

                    // 获得 MvcResult，后续执行各种自定义逻辑
                    MvcResult mvcResult = resultActions.andReturn();

                    String result = mvcResult.getResponse().getContentAsString();
                    if ("success".equals(result)) {
                        successCount.incrementAndGet();
                    }

                    System.out.println(result);
                    downLatch.countDown();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }

        // 等待所有线程都执行完任务
        downLatch.await();
        fixedThreadPool.shutdown();

        System.out.println("总共有" + successCount.get() + "个线程获得到了令牌!");

    }
}
