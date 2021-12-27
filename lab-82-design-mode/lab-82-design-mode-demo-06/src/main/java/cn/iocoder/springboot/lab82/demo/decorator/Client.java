package cn.iocoder.springboot.lab82.demo.decorator;

import cn.iocoder.springboot.lab82.demo.decorator.component.Beverage;
import cn.iocoder.springboot.lab82.demo.decorator.component.DarkRoast;
import cn.iocoder.springboot.lab82.demo.decorator.component.Espresso;

/**
 * 客户端测试类
 *
 * @author jaquez
 * @date 2021/12/27 16:10
 **/
public class Client {

    public static void main(String[] args) {

        Beverage beverage1 = new Espresso();
        System.out.println(beverage1.getDescription() + " $" + beverage1.cost());

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

    }
}
