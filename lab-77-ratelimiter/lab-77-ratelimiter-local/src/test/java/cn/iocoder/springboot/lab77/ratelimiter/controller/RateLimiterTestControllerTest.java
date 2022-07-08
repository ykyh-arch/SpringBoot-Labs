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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RateLimiterTestControllerTest
 *
 * @author jaquez
 * @date 2022/07/08 11:05
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class RateLimiterTestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testRatelimit() throws Exception {

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/ratelimit"));
        // 校验结果
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()); // 响应状态码 200
        resultActions.andExpect(MockMvcResultMatchers.content().string("success")); // 响应结果

        // 打印结果
        resultActions.andDo(MockMvcResultHandlers.print());

        // 获得 MvcResult，后续执行各种自定义逻辑
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("拦截器数量：" + mvcResult.getInterceptors().length);
    }

    @Test
    public void batchTestRatelimit() throws InterruptedException {

        // 设置线程池最大执行 20 个线程并发执行任务，AtomicInteger 通过 CAS 操作能保证统计数量的原子性
        int threadSize = 20;
        AtomicInteger successCount = new AtomicInteger(0);
        CountDownLatch downLatch = new CountDownLatch(20);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadSize);

        for (int i = 0; i < threadSize; i++) {
            fixedThreadPool.submit(() -> {

                ResultActions resultActions = null;
                try {
                    resultActions = mvc.perform(MockMvcRequestBuilders.get("/ratelimit"));
                    // 校验结果，验证不通过线程会阻塞
                    // resultActions.andExpect(MockMvcResultMatchers.status().isOk()); // 响应状态码 200
                    // resultActions.andExpect(MockMvcResultMatchers.content().string("success")); // 响应结果

                    // 打印结果
                    // resultActions.andDo(MockMvcResultHandlers.print());

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
