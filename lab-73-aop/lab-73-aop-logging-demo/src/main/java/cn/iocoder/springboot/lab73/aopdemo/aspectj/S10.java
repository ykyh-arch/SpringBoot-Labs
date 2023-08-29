package cn.iocoder.springboot.lab73.aopdemo.aspectj;

/**
 * S10
 *
 * @author jaquez
 * @date 2023/07/07 17:21
 **/
public class S10 extends S10Parent{

    @Override
    public void m2() {
        System.out.println("我是S10.m2()方法");
    }

    public void m3() {
        System.out.println("我是S10.m3()方法");
    }

}
