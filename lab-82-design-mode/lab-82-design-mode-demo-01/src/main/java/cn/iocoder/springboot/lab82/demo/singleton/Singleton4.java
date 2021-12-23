package cn.iocoder.springboot.lab82.demo.singleton;

/**
 * 第三、 用“双重检查加锁（DCL）”，在 getInstance() 中减少使用同步。
 *
 * @author jaquez
 * @date 2021/12/22 16:46
 **/
public class Singleton4 {
    // 利用静态变量来记录Singleton的唯一实例
    private static Singleton4 uniqueInstance;

    // 构造器私有化，只有Singleton类内才可以调用构造器
    private Singleton4() {
    }

    // 唯一提供访问点，检查实例，如果不存在，就进入同步区域
    public static synchronized Singleton4 getInstance() {
        // 在这里是首先检查是否实例已经创建了，如果尚未创建，才会进行同步。这样一来。只有第一次会同步
        if (uniqueInstance == null) {
            synchronized (Singleton4.class) {    // 进入同步区域
                if (uniqueInstance == null) {     // 再检查一次，如果为 null，则创建
                    uniqueInstance = new Singleton4();
                }
            }
        }
        return uniqueInstance;
    }

}
