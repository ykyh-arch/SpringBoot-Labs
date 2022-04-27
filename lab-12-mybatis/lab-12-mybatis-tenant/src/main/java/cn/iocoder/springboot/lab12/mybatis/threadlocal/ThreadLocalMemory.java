package cn.iocoder.springboot.lab12.mybatis.threadlocal;

import java.util.concurrent.CountDownLatch;

/**
 * ThreadLocalMemory 模拟内存泄露
 * 原文链接：https://blog.csdn.net/Codefarmer_uzi/article/details/120553336
 *
 * @author jaquez
 * @date 2022/04/27 16:47
 **/
public class ThreadLocalMemory {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        TestThread test = new TestThread();
        test.start();

        try {
            new CountDownLatch(1).await(); // 阻塞程序
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class TestThread extends Thread {

    @Override
    public void run() {
        System.out.println("start...");
        ThreadLocalMemory test = new ThreadLocalMemory();
        test.threadLocal.set("test");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test = null;
        // 手动 GC
        System.gc();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end....");

    }
}


