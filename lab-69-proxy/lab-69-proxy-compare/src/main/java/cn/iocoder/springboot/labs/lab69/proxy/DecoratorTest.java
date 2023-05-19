package cn.iocoder.springboot.labs.lab69.proxy;

import cn.iocoder.springboot.labs.lab69.target.Test;

/**
 * DecoratorTest 装饰者模式实现的代理
 *
 * @author jaquez
 * @date 2022/05/20 14:42
 **/
public class DecoratorTest implements Test {

    private Test target;

    public DecoratorTest(Test target) {
        this.target = target;
    }

    @Override
    public int test(int i) {
        // 方法前后可以做一些特殊业务逻辑处理，如：统计耗时
        return target.test(i);
    }

}
