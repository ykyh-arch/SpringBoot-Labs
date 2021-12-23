package cn.iocoder.springboot.lab82.demo.simplefactory;

/**
 * 奶酪披萨
 *
 * @author jaquez
 * @date 2021/12/22 17:45
 **/
public class CheesePizza extends Pizza{
    @Override
    public void bake() {
        System.out.println("bake CheesePizza ...");
    }

    @Override
    public void box() {
        System.out.println("box CheesePizza ...");
    }

    @Override
    public void cut() {
        System.out.println("cut CheesePizza ...");
    }

    @Override
    public void prepare() {
        System.out.println("prepare CheesePizza ...");
    }
}
