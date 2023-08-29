package cn.iocoder.springboot.lab73.aopdemo.aspectj;

/**
 * @author jaquez
 * @date 2023/07/10 11:15
 **/
public class S12 extends S12Parent{

    @Override
    public void m2() {
        System.out.println("我是S12.m2()方法");
    }

    @Ann12
    public void m3() {
        System.out.println("我是S12.m3()方法");
    }

    public void m4() {
        System.out.println("我是S12.m4()方法");
    }
}
