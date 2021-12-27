package cn.iocoder.springboot.lab82.demo.decorator;

import cn.iocoder.springboot.lab82.demo.decorator.component.Beverage;

/**
 * 大豆类
 *
 * @author jaquez
 * @date 2021/12/27 16:03
 **/
public class Soy extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Soy";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }

}
