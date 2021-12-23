package cn.iocoder.springboot.lab82.demo.singleton;

/**
 * 第二、 直接初始化静态变量。这样就保证了线程安全
 *
 * @author jaquez
 * @date 2021/12/22 16:46
 **/
public class Singleton3 {
    // 利用静态变量来记录Singleton的唯一实例，直接初始化静态变量，这样就可以确保线程安全了
    private static Singleton3 uniqueInstance = new Singleton3();

    // 构造器私有化，只有Singleton类内才可以调用构造器
    private Singleton3() {
    }

    // 唯一提供访问点
    public static synchronized Singleton3 getInstance() {
        return uniqueInstance;
    }

}
