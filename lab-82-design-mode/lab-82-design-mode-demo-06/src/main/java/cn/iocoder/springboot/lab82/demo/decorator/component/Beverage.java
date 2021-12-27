package cn.iocoder.springboot.lab82.demo.decorator.component;

/**
 * 情景模式：星巴兹以扩张速度快而闻名。在里面购买咖啡时，可以要求在其中加入各种调料，星巴兹会根据所加入的调料收取不同的费用，也就是说不同的咖啡与调料之间有N多不同的组合方式。每种咖啡和调料都有不同的收费。如果这个时候我们使用继承方式，则会陷入无以复加的地步。这里会有N多个类，出现“类爆炸”现象。
 * <p>
 * 酒水饮料 Component
 *
 * @author jaquez
 * @date 2021/12/27 14:05
 **/
public abstract class Beverage {

    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

}
