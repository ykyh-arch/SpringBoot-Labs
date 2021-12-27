package cn.iocoder.springboot.lab82.demo.decorator;

import cn.iocoder.springboot.lab82.demo.decorator.component.Beverage;

/**
 * 调料装饰者，抽象的装饰者类
 *
 * @author jaquez
 * @date 2021/12/27 15:00
 **/
public abstract class CondimentDecorator extends Beverage {

    public abstract String getDescription();

}
