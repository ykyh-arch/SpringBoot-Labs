package cn.iocoder.springboot.lab29.asynctask.service;

import cn.iocoder.springboot.lab29.asynctask.Application;
import com.google.common.util.concurrent.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * MoreExecutorsTest，参考自：https://mp.weixin.qq.com/s?__biz=MzA5MTkxMDQ4MQ==&mid=2648933285&idx=1&sn=f5507c251b84c3405f2fe0f7fb1da97d&chksm=88621b9bbf15928dd4c26f52b2abb0e130cde02100c432f33f0e90123b5e4b20d43017c1030e&token=1916804008&lang=zh_CN&scene=21#wechat_redirect
 *
 * @author fw001
 * @date 2023/12/05 10:58
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MoreExecutorsTest {

    protected final Log logger = LogFactory.getLog(getClass());

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        // 创建一个线程池
        ExecutorService delegate = Executors.newFixedThreadPool(5);
        try {
            ListeningExecutorService executorService = MoreExecutors.listeningDecorator(delegate);
            // 异步执行一个任务
            ListenableFuture<Integer> submit = executorService.submit(() -> {
                logger.info(System.currentTimeMillis());
                // 休眠2秒，默认耗时
                TimeUnit.SECONDS.sleep(2);
                logger.info(System.currentTimeMillis());
                return 10;
            });
            // 当任务执行完毕之后回调对应的方法
            submit.addListener(() -> {
                logger.info("任务执行完毕了，我被回调了");
            }, MoreExecutors.directExecutor());
            // 阻塞获取结果
            logger.info(submit.get());
        } finally {
            delegate.shutdown();
        }
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ExecutorService delegate = Executors.newFixedThreadPool(5);
        try {
            ListeningExecutorService executorService = MoreExecutors.listeningDecorator(delegate);
            ListenableFuture<Integer> submit = executorService.submit(() -> {
                logger.info(System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(4);
                // int i = 10 / 0;
                logger.info(System.currentTimeMillis());
                return 10;
            });
            Futures.addCallback(submit, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(@Nullable Integer result) {
                    logger.info("执行成功:"+result);
                }

                @Override
                public void onFailure(Throwable t) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.error("执行任务发生异常:" + t.getMessage(), t);
                }
            }, MoreExecutors.directExecutor());
            logger.info(submit.get());
        } finally {
            delegate.shutdown();
        }
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        logger.info("start");
        ExecutorService delegate = Executors.newFixedThreadPool(5);
        try {
            ListeningExecutorService executorService = MoreExecutors.listeningDecorator(delegate);
            List<ListenableFuture<Integer>> futureList = new ArrayList<>();
            for (int i = 5; i >= 0; i--) {
                int j = i;
                futureList.add(executorService.submit(() -> {
                    TimeUnit.SECONDS.sleep(j);
                    return j;
                }));
            }
            // 获取一批任务的执行结果
            List<Integer> resultList = Futures.allAsList(futureList).get();
            // 输出
            resultList.forEach(logger::info);
        } finally {
            delegate.shutdown();
        }
    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        logger.info("start");
        ExecutorService delegate = Executors.newFixedThreadPool(5);
        try {
            ListeningExecutorService executorService = MoreExecutors.listeningDecorator(delegate);
            List<ListenableFuture<Integer>> futureList = new ArrayList<>();
            for (int i = 5; i >= 0; i--) {
                int j = i;
                futureList.add(executorService.submit(() -> {
                    TimeUnit.SECONDS.sleep(j);
                    return j;
                }));
            }
            ListenableFuture<List<Integer>> listListenableFuture = Futures.allAsList(futureList);
            Futures.addCallback(listListenableFuture, new FutureCallback<List<Integer>>() {
                @Override
                public void onSuccess(@Nullable List<Integer> result) {
                    logger.info("result中所有结果之和：" + result.stream().reduce(Integer::sum).get());
                }

                @Override
                public void onFailure(Throwable t) {
                    logger.error("执行任务发生异常:" + t.getMessage(), t);
                }
            }, MoreExecutors.directExecutor());
        } finally {
            delegate.shutdown();
        }
    }

}
