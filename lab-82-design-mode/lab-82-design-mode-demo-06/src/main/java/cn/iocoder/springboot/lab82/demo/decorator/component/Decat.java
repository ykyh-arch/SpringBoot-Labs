package cn.iocoder.springboot.lab82.demo.decorator.component;

import cn.iocoder.springboot.lab82.demo.decorator.component.Beverage;

/**
 * 具体组件实现类
 *
 * @author jaquez
 * @date 2021/12/27 14:17
 **/
public class Decat extends Beverage {

    public Decat() {
        description = "Decat";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}