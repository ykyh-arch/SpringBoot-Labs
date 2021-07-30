package cn.iocoder.springboot.lab27.springwebflux.servlet;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 模拟慢程序
 * @author jaquez
 * @date 2021/07/28 16:59
 **/
public class LongRunningProcess {
    public void run() {
        try {
            // 阻塞2s
            int millis = ThreadLocalRandom.current().nextInt(2000);
            String currentThreadName = Thread.currentThread().getName();
            System.out.println(currentThreadName + " sleep for " + millis + " milliseconds.");
            Thread.sleep(millis);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
