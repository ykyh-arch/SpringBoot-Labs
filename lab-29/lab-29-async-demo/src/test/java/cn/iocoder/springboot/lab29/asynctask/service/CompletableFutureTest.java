package cn.iocoder.springboot.lab29.asynctask.service;

import cn.iocoder.springboot.lab29.asynctask.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * CompletableFutureTest 异步任务测试类，参考自：https://mp.weixin.qq.com/s/xVcBrRr4FaFPbWqeeN2UFw
 *
 * @author jaquez
 * @date 2023/02/09 17:17
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CompletableFutureTest {

    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "hello";
        });
        System.out.println(future.get());
        System.out.println("end");
    }

    @Test
    public void testCountDownLatch() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch downLatch = new CountDownLatch(2);
        long startTime = System.currentTimeMillis();
        Future<String> userFuture = executorService.submit(() -> {
            // 模拟查询商品耗时 500 毫秒
            Thread.sleep(500);
            downLatch.countDown();
            return "用户A";
        });

        Future<String> goodsFuture = executorService.submit(() -> {
            // 模拟查询商品耗时 500 毫秒
            Thread.sleep(400);
            downLatch.countDown();
            return "商品A";
        });

        downLatch.await();
        // 模拟主程序耗时时间
        Thread.sleep(600);
        System.out.println("获取用户信息:" + userFuture.get());
        System.out.println("获取商品信息:" + goodsFuture.get());
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");

    }

    // 通过 CompletableFuture 可以很轻松的实现 CountDownLatch 的功能
    @Test
    public void testCompletableInfo() throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();

        // 调用用户服务获取用户基本信息
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() ->
                // 模拟查询商品耗时 500 毫秒
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "用户A";
        });

        // 调用商品服务获取商品基本信息
        CompletableFuture<String> goodsFuture = CompletableFuture.supplyAsync(() ->
                // 模拟查询商品耗时 500 毫秒
        {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "商品A";
        });

        System.out.println("获取用户信息:" + userFuture.get());
        System.out.println("获取商品信息:" + goodsFuture.get());

        // 模拟主程序耗时时间
        Thread.sleep(600);
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    @Test
    public void testCompletableGet() throws InterruptedException, ExecutionException {

        CompletableFuture<String> cp1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "商品A";
        });

        // getNow 方法测试
        System.out.println(cp1.getNow("商品B"));

        // join 方法测试
        CompletableFuture<Integer> cp2 = CompletableFuture.supplyAsync((() -> 1 / 0));
        System.out.println(cp2.join());
        System.out.println("-----------------------------------------------------");
        // get 方法测试
        CompletableFuture<Integer> cp3 = CompletableFuture.supplyAsync((() -> 1 / 0));
        System.out.println(cp3.get());
    }

    // 「做完第一个任务后，再做第二个任务,第二个任务也没有返回值」
    @Test
    public void testCompletableThenRunAsync() throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();

        CompletableFuture<Void> cp1 = CompletableFuture.
                runAsync(() -> {
            try {
                // 执行任务A
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        CompletableFuture<Void> cp2 =  cp1.thenRun(() -> {
            try {
                // 执行任务B
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // get方法测试
        System.out.println(cp2.get());

        // 模拟主程序耗时时间
        Thread.sleep(600);
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    // 第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果，作为入参，传递到回调方法中，但是回调方法是没有返回值的。
    @Test
    public void testCompletableThenAccept() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> cp1 = CompletableFuture.supplyAsync(() -> {
            return "dev";

        });
        CompletableFuture<Void> cp2 =  cp1.thenAccept((a) -> {
            System.out.println("上一个任务的返回结果为: " + a);
        });

        cp2.get();
    }

    // 表示第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果，作为入参，传递到回调方法中，并且回调方法是有返回值的。
    @Test
    public void testCompletableThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cp1 = CompletableFuture.supplyAsync(() -> {
            return "dev";

        }).thenApply((a) -> {
            if(Objects.equals(a,"dev")){
                return "dev";
            }
            return "prod";
        });

        System.out.println("当前环境为:" + cp1.get());

    }

    @Test
    public void testCompletableWhenComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> {

            if (Math.random() < 0.5) {
                throw new RuntimeException("出错了");
            }
            System.out.println("正常结束");
            return 0.11;

        }).whenComplete((aDouble, throwable) -> {
            if (aDouble == null) {
                System.out.println("whenComplete aDouble is null");
            } else {
                System.out.println("whenComplete aDouble is " + aDouble);
            }
            if (throwable == null) {
                System.out.println("whenComplete throwable is null");
            } else {
                System.out.println("whenComplete throwable is " + throwable.getMessage());
            }
        });
        System.out.println("最终返回的结果 = " + future.get());
    }

    @Test
    public void testWhenCompleteExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> {
            if (Math.random() < 0.5) {
                throw new RuntimeException("出错了");
            }
            System.out.println("正常结束");
            return 0.11;

        }).whenComplete((aDouble, throwable) -> {
            if (aDouble == null) {
                System.out.println("whenComplete aDouble is null");
            } else {
                System.out.println("whenComplete aDouble is " + aDouble);
            }
            if (throwable == null) {
                System.out.println("whenComplete throwable is null");
            } else {
                System.out.println("whenComplete throwable is " + throwable.getMessage());
            }
        }).exceptionally((throwable) -> {
            System.out.println("exceptionally中异常：" + throwable.getMessage());
            // 出现异常返回默认值
            return 0.0;
        });

        System.out.println("最终返回的结果 = " + future.get());
    }

    // 多任务组合方法，thenCombine / thenAcceptBoth / runAfterBoth都表示：「当任务一和任务二都完成再执行任务三」
    @Test
    public void testCompletableThenCombine() throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 开启异步任务 1
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务1，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 1;
            System.out.println("异步任务1结束");
            return result;
        }, executorService);

        // 开启异步任务 2
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务2，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 1;
            System.out.println("异步任务2结束");
            return result;
        }, executorService);

        // 任务组合
        CompletableFuture<Integer> task3 = task.thenCombineAsync(task2, (f1, f2) -> {
            System.out.println("执行任务3，当前线程是：" + Thread.currentThread().getId());
            System.out.println("任务1返回值：" + f1);
            System.out.println("任务2返回值：" + f2);
            return f1 + f2;
        }, executorService);

        Integer res = task3.get();
        System.out.println("最终结果：" + res);

    }

    // OR组合关系，applyToEither / acceptEither / runAfterEither 都表示：「两个任务，只要有一个任务完成，就执行任务三」
    @Test
    public void testCompletableEitherAsync() {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 开启异步任务 1
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务1，当前线程是：" + Thread.currentThread().getId());

            int result = 1 + 1;
            System.out.println("异步任务1结束");
            return result;
        }, executorService);

        //开启异步任务 2
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务2，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 2;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步任务2结束");
            return result;
        }, executorService);

        // 任务组合
        task.acceptEitherAsync(task2, (res) -> {
            System.out.println("执行任务3，当前线程是：" + Thread.currentThread().getId());
            System.out.println("上一个任务的结果为："+res);
        }, executorService);
    }

    // 多任务组合，「allOf」：等待所有任务完成；「anyOf」：只要有一个任务完成
    @Test
    public void testCompletableAllOf() throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 开启异步任务 1
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务1，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 1;
            System.out.println("异步任务1结束");
            return result;
        }, executorService);

        //开启异步任务 2
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务2，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 2;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步任务2结束");
            return result;
        }, executorService);

        // 开启异步任务 3
        CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务3，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 3;
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步任务3结束");
            return result;
        }, executorService);

        //任务组合
        CompletableFuture<Void> allOf = CompletableFuture.allOf(task, task2, task3);

        //等待所有任务完成
        allOf.get();
        //获取任务的返回结果
        System.out.println("task结果为：" + task.get());
        System.out.println("task2结果为：" + task2.get());
        System.out.println("task3结果为：" + task3.get());
    }

    @Test
    public void testCompletableAnyOf() throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 开启异步任务 1
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
            int result = 1 + 1;
            return result;
        }, executorService);

        // 开启异步任务 2
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            int result = 1 + 2;
            return result;
        }, executorService);

        // 开启异步任务 3
        CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
            int result = 1 + 3;
            return result;
        }, executorService);

        // 任务组合
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(task, task2, task3);
        // 只要有一个有任务完成
        Object o = anyOf.get();
        System.out.println("完成的任务的结果：" + o);
    }

}
