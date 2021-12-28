package cn.iocoder.springboot.lab29.asynctask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

@Service
public class DemoService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Async
    public Integer execute01Async() {
        return this.execute01();
    }

    @Async
    public Integer execute02Async() {
        return this.execute02();
    }

    /**
     * 异步执行任务
     * 类：AsyncResult（成功forValue（），失败forExecutionException（））
     * ->接口：ListenableFuture（addCallback（）、completable（））->Future（isDone（）、get（））
     * ->实现类：ListenableFutureTask（addCallback（）、completable（）、done（））->ListenableFutureCallbackRegistry
     * @return
     */
    @Async
    public Future<Integer> execute01AsyncWithFuture() {
        return AsyncResult.forValue(this.execute01());
    }

    @Async
    public Future<Integer> execute02AsyncWithFuture() {
        return AsyncResult.forValue(this.execute02());
    }

    @Async
    public ListenableFuture<Integer> execute01AsyncWithListenableFuture() {
        try {
            //成功结果
            return AsyncResult.forValue(this.execute01());
        } catch (Throwable ex) {
            //执行失败
            return AsyncResult.forExecutionException(ex);
        }
    }

    public Integer execute01() {
        logger.info("[execute01]");
        sleep(10);
        return 1;
    }

    public Integer execute02() {
        logger.info("[execute02]");
        sleep(5);
        return 2;
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public Integer zhaoDaoNvPengYou(Integer a, Integer b) {
        throw new RuntimeException("程序员不需要女朋友");
    }

}
