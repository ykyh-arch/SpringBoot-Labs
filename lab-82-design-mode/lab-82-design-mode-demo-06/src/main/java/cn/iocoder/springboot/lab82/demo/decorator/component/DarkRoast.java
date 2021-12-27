package cn.iocoder.springboot.lab82.demo.decorator.component;

import cn.iocoder.springboot.lab82.demo.decorator.component.Beverage;

/**
 * 具体组件实现类
 *
 * @author jaquez
 * @date 2021/12/27 14:11
 **/
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 1.05;
    }

}
