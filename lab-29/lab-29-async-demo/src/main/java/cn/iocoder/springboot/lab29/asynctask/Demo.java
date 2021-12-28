package cn.iocoder.springboot.lab29.asynctask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 参考：https://www.cnblogs.com/robothy/p/12101491.html，https://blog.csdn.net/m0_37542889/article/details/92640903
 *
 */
public class Demo {

    public static void main(String[] args) {
        // 创建线程池。这里只是临时测试，不要扣艿艿遵守阿里 Java 开发规范，YEAH
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 提交任务到线程池中执行。
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("线程id："+Thread.currentThread().getId()+"，听说我被异步调用了");
            }

        });

    }

}
