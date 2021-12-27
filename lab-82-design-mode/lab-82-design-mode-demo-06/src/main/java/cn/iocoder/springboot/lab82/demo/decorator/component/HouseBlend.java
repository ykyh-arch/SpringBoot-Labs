package cn.iocoder.springboot.lab82.demo.decorator.component;

import cn.iocoder.springboot.lab82.demo.decorator.component.Beverage;

/**
 * 具体组件实现类
 *
 * @author jaquez
 * @date 2021/12/27 14:09
 **/
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 0.89;
    }

}
