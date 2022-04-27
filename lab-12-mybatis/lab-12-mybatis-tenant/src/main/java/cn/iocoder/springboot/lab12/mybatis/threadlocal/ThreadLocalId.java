package cn.iocoder.springboot.lab12.mybatis.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal 原理解析
 * ThreadLocalId，参考：https://mp.weixin.qq.com/s?__biz=Mzg4ODA3NTk0Nw==&mid=2247518141&idx=1&sn=7fec4f890bbfc87c6da2542111a7fffd&chksm=cf826479f8f5ed6f45704c8ba080843d101e99c5d4c9bd0792563ba7f8040b028e3b13254510&scene=21#wechat_redirect
 *
 * @author jaquez
 * @date 2022/04/27 14:07
 **/
public class ThreadLocalId {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return nextId.incrementAndGet();
        }
    };

    private static int get(){
        return threadId.get();
    }

    private static void remove(){
        threadId.remove();
    }

    private static void incrementSamethreadId(){
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread()+"_"+i+",threadId="+ThreadLocalId.get());
        }
    }

    // 结论：不同线程间 id 不同，相同线程 id 相同
    public static void main(String[] args) {
        incrementSamethreadId(); // 主线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                incrementSamethreadId();
            }
        }).start(); // 子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                incrementSamethreadId();
            }
        }).start(); // 子线程
    }

}
