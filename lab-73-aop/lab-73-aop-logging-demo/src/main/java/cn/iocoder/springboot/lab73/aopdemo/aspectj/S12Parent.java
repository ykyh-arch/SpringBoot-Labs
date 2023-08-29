package cn.iocoder.springboot.lab73.aopdemo.aspectj;

/**
 * @author jaquez
 * @date 2023/07/10 11:15
 **/
public class S12Parent {

    @Ann12
    public void m1() {
        System.out.println("我是S12Parent.m1()方法");
    }

    @Ann12
    public void m2() {
        System.out.println("我是S12Parent.m2()方法");
    }

}
