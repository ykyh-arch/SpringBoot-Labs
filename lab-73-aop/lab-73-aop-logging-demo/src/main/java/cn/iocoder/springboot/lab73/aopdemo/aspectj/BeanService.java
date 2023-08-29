package cn.iocoder.springboot.lab73.aopdemo.aspectj;

/**
 * @author jaquez
 * @date 2023/07/10 11:28
 **/
public class BeanService {

    private String beanName;

    public BeanService(String beanName) {
        this.beanName = beanName;
    }

    public void m1() {
        System.out.println(this.beanName);
    }
}
