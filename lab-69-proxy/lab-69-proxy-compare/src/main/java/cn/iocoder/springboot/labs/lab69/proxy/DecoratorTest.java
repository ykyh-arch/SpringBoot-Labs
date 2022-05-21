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

    public int test(int i) {
        return target.test(i);
    }

}
