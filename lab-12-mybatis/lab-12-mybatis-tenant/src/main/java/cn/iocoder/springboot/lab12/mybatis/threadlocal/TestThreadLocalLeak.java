package cn.iocoder.springboot.lab12.mybatis.threadlocal;

import java.lang.ref.WeakReference;

/**
 * TestThreadLocalLeak
 *
 * @author jaquez
 * @date 2022/04/27 16:38
 **/
public class TestThreadLocalLeak {

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
