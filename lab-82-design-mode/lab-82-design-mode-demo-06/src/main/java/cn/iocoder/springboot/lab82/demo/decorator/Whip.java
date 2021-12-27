package cn.iocoder.springboot.lab82.demo.decorator;

import cn.iocoder.springboot.lab82.demo.decorator.component.Beverage;

/**
 * Whip 类
 *
 * @author jaquez
 * @date 2021/12/27 16:06
 **/
public class Whip extends CondimentDecorator {

    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " , Whip";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.20;
    }
}
