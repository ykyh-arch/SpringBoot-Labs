package cn.iocoder.springboot.lab29.asynctask.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 批量更新优化方案，参考：https://mp.weixin.qq.com/s/6Uxv1TKNBI_oF3r4WrK3KQ
 *
 * SplitListUtils 拆分集合的工具类，将大集合拆分成多个小集合
 *
 * @author jaquez
 * @date 2022/06/28 14:33
 **/
public class SplitListUtils {

    /**
     * 拆分集合
     *
     * @param <T> 泛型对象
     * @param resList 需要拆分的集合
     * @param subListLength 每个子集合的元素个数
     * @return 返回拆分后的各个集合组成的列表
     * 代码里面用到了 guava 和 common 的结合工具类
     **/
    public static <T> List<List<T>> split(List<T> resList, int subListLength) {
        if (CollectionUtils.isEmpty(resList) || subListLength <= 0) {
            return Lists.newArrayList();
        }
        List<List<T>> ret = Lists.newArrayList();
        int size = resList.size();
        if (size <= subListLength) {
            // 数据量不足 subListLength 指定的大小
            ret.add(resList);
        } else {
            int pre = size / subListLength;
            int last = size % subListLength;
            // 前面 pre 个集合，每个大小都是 subListLength 个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = Lists.newArrayList();
                for (int j = 0; j < subListLength; j++) {
                    itemList.add(resList.get(i * subListLength + j));
                }
                ret.add(itemList);
            }
            // last 的进行处理
            if (last > 0) {
                List<T> itemList = Lists.newArrayList();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * subListLength + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }

    // 测试数据
    public static void main(String[] args) throws InterruptedException {
        List<String> list = Lists.newArrayList();
        int size = 1099;
        for (int i = 1; i <= size; i++) {
            list.add("data_" + i);
        }
        System.out.println(String.format("source:%s -> size:%s,data:%s", 0, list.size(), list));
        // 大集合里面包含多个小集合
        List<List<String>> temps = split(list, 100);
        int j = 0;
        // 对大集合里面的每一个小集合进行操作
        for (List<String> obj : temps) {
            System.out.println(String.format("row:%s -> size:%s,data:%s", ++j, obj.size(), obj));
        }

        threadMethod(list);
    }

    public static void threadMethod(List totalList) throws InterruptedException {

        // 初始化线程池, 参数一定要一定要一定要调好！！！！，拒绝策略参考：https://blog.csdn.net/fen_fen/article/details/122909142
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(8, 16,
                4, TimeUnit.SECONDS, new ArrayBlockingQueue(50), new ThreadPoolExecutor.AbortPolicy());

        // 大集合拆分成 N 个小集合, 这里集合的 size 可以稍微小一些（这里我用 100 刚刚好）, 以保证多线程异步执行, 过大容易回到单线程
        List<List<String>> splitNList = SplitListUtils.split(totalList, 100);

        // 记录单个任务的执行次数
        CountDownLatch countDownLatch = new CountDownLatch(splitNList.size());
        // 对拆分的集合进行批量处理，先拆分的集合，再多线程执行
        for (List<String> singleList : splitNList) {
            // 线程池执行
            threadPool.execute(new Thread(new Runnable(){
                @Override
                public void run() {
                    int j = 0;
                    for (String str : singleList) {
                        // 数据封装，并添加到一个用于存储更新数据的 updateList
                        System.out.println(String.format("thread:%s -> row:%s -> size:%s,data:%s", Thread.currentThread().getName(),++j, singleList.size(), str));
                    }
                    // 批量更新，通过 mybatis 的批量插入的方式来进行数据的插入，这一步还是要做判空
//                    if (CollectionUtils.isEmpty(updateList)) {
//                        batchUpdate(updateList);
//                    }
                    // 任务个数 - 1, 直至为 0 时唤醒 await()
                    countDownLatch.countDown();
                }
            }));
        }
        try {
            // 让当前线程处于阻塞状态，直到锁存器计数为零
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new InterruptedException();
        }finally {
            System.out.println("任务处理完毕！");
            threadPool.shutdown();
        }

    }

}
