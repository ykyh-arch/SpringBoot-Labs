package cn.iocoder.springboot.lab82.demo.singleton;

/**
 * 第一、 使用 synchronized 来处理。也就是说将 getInstance()方法变成同步方法即可
 *
 * @author jaquez
 * @date 2021/12/22 16:46
 **/
public class Singleton2 {
    // 利用静态变量来记录Singleton的唯一实例
    private static Singleton2 uniqueInstance;

    // 构造器私有化，只有Singleton类内才可以调用构造器
    private Singleton2() {
    }

    // 唯一提供访问点
    public static synchronized Singleton2 getInstance() {
        if (uniqueInstance == null) {
            // 问题，多线程可能会创建多个对象
            uniqueInstance = new Singleton2();
        }
        return uniqueInstance;
    }

}
