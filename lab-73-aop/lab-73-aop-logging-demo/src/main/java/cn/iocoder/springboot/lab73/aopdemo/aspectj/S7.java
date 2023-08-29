package cn.iocoder.springboot.lab73.aopdemo.aspectj;

/**
 * @author jaquez
 * @date 2023/07/10 11:01
 **/
public class S7 extends S7Parent{

    public void m1() {
        System.out.println("我是m1");
    }

    public static void main(String[] args) {
        System.out.println(S7.class.getAnnotation(Ann7.class));
    }
}
