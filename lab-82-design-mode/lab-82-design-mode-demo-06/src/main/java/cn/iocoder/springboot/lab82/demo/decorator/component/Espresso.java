package cn.iocoder.springboot.lab82.demo.decorator.component;

/**
 * 具体组件实现类
 *
 * @author jaquez
 * @date 2021/12/27 14:14
 **/
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
