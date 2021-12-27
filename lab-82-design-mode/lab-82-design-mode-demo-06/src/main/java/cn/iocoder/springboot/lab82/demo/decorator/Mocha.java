package cn.iocoder.springboot.lab82.demo.decorator;

import cn.iocoder.springboot.lab82.demo.decorator.component.Beverage;

/**
 * 摩卡类
 *
 * @author jaquez
 * @date 2021/12/27 15:05
 **/
public class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " , Mocha";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.20;
    }

}
