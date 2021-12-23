package cn.iocoder.springboot.lab82.demo.singleton;

/**
 * 单例模式演示，参考：https://www.iocoder.cn/DesignPattern/xiaomingge/Singleton-Pattern/
 * <p>
 * 特点
 * <p>
 * 一、它只有一个实例。
 * <p>
 * 二、它必须要自行实例化。
 * <p>
 * 三、它必须自行想整个系统提供访问点。
 * <p>
 * 适用场景
 * <p>
 * 无状态、全局唯一、控制资源访问（线程池）
 *
 * @author jaquez
 * @date 2021/12/22 16:46
 **/
public class Singleton1 {
    // 利用静态变量来记录Singleton的唯一实例
    private static Singleton1 uniqueInstance;

    // 构造器私有化，只有Singleton类内才可以调用构造器
    private Singleton1() {
    }

    // 唯一提供访问点
    public static Singleton1 getInstance() {
        if (uniqueInstance == null) {
            // 问题，多线程可能会创建多个对象，有三种解决方案
            uniqueInstance = new Singleton1();
        }
        return uniqueInstance;
    }

}
