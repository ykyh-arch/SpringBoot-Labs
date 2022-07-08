package cn.iocoder.springboot.lab77.ratelimiter.semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore
 *
 * @author jaquez
 * @date 2022/07/08 11:49
 **/
public class SemaphoreTest {

    private final static Semaphore permit = new Semaphore(5);

    public static void main(String[] args) throws InterruptedException {
        // 设置线程池最大执行 20 个线程并发执行任务
        int threadSize = 20;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadSize);
        CountDownLatch downLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            fixedThreadPool.submit(() -> {
                try {
                    // 获取令牌
                    permit.acquire();
                    Thread.sleep(1L);
                    //业务逻辑处理
                    System.out.println("处理任务的线程是" + Thread.currentThread().getId() + ",当前时间是" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放令牌
                    permit.release();
                    downLatch.countDown();
                }
            });
        }
        downLatch.await();
        fixedThreadPool.shutdown();
    }
}
