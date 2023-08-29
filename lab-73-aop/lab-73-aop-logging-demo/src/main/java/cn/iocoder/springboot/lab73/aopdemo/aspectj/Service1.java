package cn.iocoder.springboot.lab73.aopdemo.aspectj;

/**
 * Service1
 *
 * @author jaquez
 * @date 2023/07/03 11:32
 **/
public class Service1 {

    public void m1() {
        System.out.println("我是 m1 方法");
    }

    public void m2() {
        System.out.println(10 / 0);
        System.out.println("我是 m2 方法");
    }
}
