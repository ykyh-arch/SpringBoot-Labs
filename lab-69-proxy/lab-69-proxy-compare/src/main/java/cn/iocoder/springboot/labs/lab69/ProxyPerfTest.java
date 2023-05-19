package cn.iocoder.springboot.labs.lab69;

import cn.iocoder.springboot.labs.lab69.proxy.CglibProxyTest;
import cn.iocoder.springboot.labs.lab69.proxy.DecoratorTest;
import cn.iocoder.springboot.labs.lab69.proxy.DynamicProxyTest;
import cn.iocoder.springboot.labs.lab69.target.Test;
import cn.iocoder.springboot.labs.lab69.target.TestImpl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * ProxyPerfTester 对比三种代理进行调用的耗时测试，参考自：https://www.iocoder.cn/Fight/The-running-performance-of-Cglib-compared-to-the-JDK-dynamic-proxy/
 * 另一篇文章参考：https://mp.weixin.qq.com/s?__biz=MzA5MTkxMDQ4MQ==&mid=2648934082&idx=1&sn=c919886400135a0152da23eaa1f276c7&chksm=88621efcbf1597eab943b064147b8fb8fd3dfbac0dc03f41d15d477ef94b60d4e8f78c66b262&token=1042984313&lang=zh_CN#rd
 *
 * @author jaquez
 * @date 2022/05/20 15:05
 **/
public class ProxyPerfTest {

    public static void main(String[] args) throws InterruptedException {
        // 创建测试对象；查看代理对象参考：https://www.yisu.com/zixun/597206.html
        Test nativeTest = new TestImpl(); // cn.iocoder.springboot.labs.lab69.target.TestImpl@68c4039c
        Test decorator = new DecoratorTest(nativeTest); // cn.iocoder.springboot.labs.lab69.proxy.DecoratorTest@ae45eb6
        Test dynamicProxy = DynamicProxyTest.newProxyInstance(nativeTest); // cn.iocoder.springboot.labs.lab69.target.TestImpl@68c4039c $Proxy0@68c4039c
        Test cglibProxy = CglibProxyTest.newProxyInstance(TestImpl.class); // cn.iocoder.springboot.labs.lab69.target.TestImpl$$EnhancerByCGLIB$$5bc47f5c@239963d8

        // 预热一下，一万；
        int preRunCount = 10000;
        runWithoutMonitor(nativeTest, preRunCount);
        runWithoutMonitor(decorator, preRunCount);
        runWithoutMonitor(cglibProxy, preRunCount);
        runWithoutMonitor(dynamicProxy, preRunCount);

        // 执行测试；
        Map<String, Test> tests = new LinkedHashMap<String, Test>();
        tests.put("Native   ", nativeTest);
        tests.put("Decorator", decorator);
        tests.put("Dynamic  ", dynamicProxy);
        tests.put("Cglib    ", cglibProxy);
        int repeatCount = 3;
        // 一百万
        int runCount = 1000000;
        runTest(repeatCount, runCount, tests);
        // 五千万
        runCount = 50000000;
        runTest(repeatCount, runCount, tests);
        // 阻塞测试一下
        // new CountDownLatch(1).await();
    }

    /**
     * 测试
     * @param repeatCount
     * @param runCount
     * @param tests
     */
    private static void runTest(int repeatCount, int runCount, Map<String, Test> tests){
        System.out.println(String.format("\n==================== run test : [repeatCount=%s] [runCount=%s] [java.version=%s] ====================", repeatCount, runCount, System.getProperty("java.version")));
        for (int i = 0; i < repeatCount; i++) {
            System.out.println(String.format("\n--------- test : [%s] ---------", (i+1)));
            for (String key : tests.keySet()) {
                runWithMonitor(tests.get(key), runCount, key);
            }
        }
    }

    private static void runWithoutMonitor(Test test, int runCount) {
        for (int i = 0; i < runCount; i++) {
            test.test(i);
        }
    }

    private static void runWithMonitor(Test test, int runCount, String tag) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < runCount; i++) {
            test.test(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("["+tag + "] Elapsed Time:" + (end-start) + "ms");
    }

}
