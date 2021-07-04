package cn.iocoder.springboot.lab29.asynctask.service;

import cn.iocoder.springboot.lab29.asynctask.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DemoServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoService demoService;

    @Test
    public void task01() {
        long now = System.currentTimeMillis();
        logger.info("[task01][开始执行]");

        demoService.execute01();
        demoService.execute02();
        //[task01][结束执行，消耗时长 15024 毫秒]，同步执行完成
        logger.info("[task01][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    @Test
    public void task02() {
        long now = System.currentTimeMillis();
        logger.info("[task02][开始执行]");

        demoService.execute01Async();
        demoService.execute02Async();
        //[task02][结束执行，消耗时长 40 毫秒]，异步执行，此时下面执行完，上面两个方法，并没有执行完成
        logger.info("[task02][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    /**
     * 等待异步调用完成，在一些业务场景中，我们希望达到异步调用的效果，同时主线程阻塞等待异步调用的结果。
     * @author Jaquez
     * @date 2021/07/04 17:17
     * @return void
     */
    @Test
    public void task03() throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        logger.info("[task03][开始执行]");

        // 异步执行任务
        Future<Integer> execute01Result = demoService.execute01AsyncWithFuture();
        Future<Integer> execute02Result = demoService.execute02AsyncWithFuture();
        // 主线程阻塞等待结果
        execute01Result.get();
        execute02Result.get();
        //[task03][结束执行，消耗时长 10078 毫秒]
        logger.info("[task03][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    /**
     * 自定义异步任务+回调
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void task04() throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        logger.info("[task04][开始执行]");

        // 执行任务，返回的是ListenableFutureTask
        ListenableFuture<Integer> execute01Result = demoService.execute01AsyncWithListenableFuture();
        logger.info("[task04][execute01Result 的类型是：({})]",execute01Result.getClass().getSimpleName());
        execute01Result.addCallback(new SuccessCallback<Integer>() { // 增加成功的回调

            @Override
            public void onSuccess(Integer result) {
                logger.info("[onSuccess][result: {}]", result);
            }

        }, new FailureCallback() { // 增加失败的回调
            @Override
            public void onFailure(Throwable ex) {
                logger.info("[onFailure][发生异常]", ex);
            }

        });
        execute01Result.addCallback(new ListenableFutureCallback<Integer>() { // 增加成功和失败的统一回调

            @Override
            public void onSuccess(Integer result) {
                logger.info("[onSuccess][result: {}]", result);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info("[onFailure][发生异常]", ex);
            }

        });
        // 阻塞等待结果
        execute01Result.get();

        logger.info("[task04][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    @Test
    public void testZhaoDaoNvPengYou() throws InterruptedException {
        demoService.zhaoDaoNvPengYou(1, 2);

        // sleep 1 秒，保证异步调用的执行
        Thread.sleep(1000);
    }

}
