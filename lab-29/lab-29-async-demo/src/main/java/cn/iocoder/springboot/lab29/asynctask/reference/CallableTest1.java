package cn.iocoder.springboot.lab29.asynctask.reference;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 使用 Callable + FutureTask 获取执行结果
 *
 * @author jaquez
 * @date 2021/07/05 10:59
 **/
public class CallableTest1 {
    public static void main(String[] args) {

        // 创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        // 创建 Callable 对象任务
        CallableDemo calTask=new CallableDemo();
        // 创建 FutureTask
        FutureTask<Integer> futureTask=new FutureTask<>(calTask);
        // 执行任务
        es.submit(futureTask);
        // 关闭线程池
        es.shutdown();
        try {
            Thread.sleep(2000);
            System.out.println("主线程在执行其他任务");

            if(futureTask.get()!=null){
                //输出获取到的结果
                System.out.println("futureTask.get()-->"+futureTask.get());
            }else{
                //输出获取到的结果
                System.out.println("futureTask.get()未获取到结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行完成");
    }

}
