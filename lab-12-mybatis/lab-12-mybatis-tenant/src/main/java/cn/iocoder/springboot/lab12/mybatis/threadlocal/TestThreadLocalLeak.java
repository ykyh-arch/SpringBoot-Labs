package cn.iocoder.springboot.lab12.mybatis.threadlocal;

import java.lang.ref.WeakReference;

/**
 * TestThreadLocalLeak
 *
 * @author jaquez
 * @date 2022/04/27 16:38
 **/
public class TestThreadLocalLeak {

    // JVM 调优思路：https://www.cnblogs.com/ZT-666/p/15811813.html
    // 打印 GC 信息，-Xloggc:/opt/xxx/logs/xxx-xxx-gc-%t.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=20M -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCCause (gc日志文件路径，循环打印，一共5个日志文件，每个文件大小20mb，打印gc详细信息，发生gc的时间和原因)
    public static void main(String[] args) {
        // String str  = new String("TestThreadLocalLeak !");
        // WeakReference<String> weakReference = new WeakReference<>(str); // 強引用，不会被回收
        WeakReference<String> weakReference = new WeakReference<>(new String("TestThreadLocalLeak !")); // 弱引用，会被回收
        // 手动 GC
        System.gc();
        if(weakReference.get() == null){
            System.out.println("str 已经被 GC 回收了！");
        }else {
            System.out.println(weakReference.get());
        }

    }
}
