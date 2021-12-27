package cn.iocoder.springboot.lab82.demo.decorator;

import cn.iocoder.springboot.lab82.demo.decorator.component.Beverage;

/**
 * 牛奶类
 *
 * @author jaquez
 * @date 2021/12/27 15:02
 **/
public class Milk extends CondimentDecorator {

    Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " , Milk";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.3;
    }

}
