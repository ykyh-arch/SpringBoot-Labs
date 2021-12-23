package cn.iocoder.springboot.lab82.demo.simplefactory;

/**
 * 披萨父类
 *
 * @author jaquez
 * @date 2021/12/22 17:43
 **/
public abstract class Pizza {

    public abstract void prepare();

    public abstract void bake();

    public abstract void cut();

    public abstract void box();
}
