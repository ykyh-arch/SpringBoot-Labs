package cn.iocoder.springboot.lab29.asynctask.reference;

import java.util.concurrent.Callable;

/**
 * 有这样的场景，我们现在需要计算一个数据，而这个数据的计算比较耗时，
 * 而我们后面的程序也要用到这个数据结果，那么这个时 Callable岂不是最好的选择？
 * 我们可以开设一个线程去执行计算，而主线程继续做其他事，而后面需要使用到这个数据时，
 * 我们再使用Future获取不就可以了吗？
 * 使用Callable+Future获取执行结果
 * 参考：https://blog.csdn.net/javazejian/article/details/50896505
 * @author jaquez
 * @date 2021/07/05 10:53
 **/
public class CallableDemo implements Callable<Integer> {
    private int sum;
    @Override
    public Integer call() throws Exception {
        System.out.println("Callable子线程开始计算啦！");
        Thread.sleep(2000);

        for(int i=0 ;i<5000;i++){
            sum=sum+i;
        }
        System.out.println("Callable子线程计算结束！");
        return sum;
    }
}
