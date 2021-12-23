package cn.iocoder.springboot.lab82.demo.abstractfactory;

import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.cheese.Cheese;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.clams.Clams;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.dough.Dough;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.pepperoni.Pepperoni;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.sauce.Sauce;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.veggies.Veggies;

/**
 * 披萨原料工厂，定义了如何生产一个相关产品的家族。
 * <p>
 * 参考：https://www.iocoder.cn/DesignPattern/xiaomingge/Abstract-Factory-Pattern/
 * <p>
 * 在工厂方法模式中，我们使用一个工厂创建一个产品，也就是说一个具体的工厂对应一个具体的产品。但是有时候我们需要一个工厂能够提供多个产品对象，而不是单一的对象，这个时候我们就需要使用抽象工厂模式。
 *
 * @author jaquez
 * @date 2021/12/23 11:26
 **/
public interface PizzaIngredientFactory {

    /*
     * 在接口中，每个原料都有一个对应的方法创建该原料
     */
    public Dough createDough();// 面团

    public Sauce createSauce(); // 酱汁

    public Cheese createCheese(); // 奶酪

    public Veggies[] createVeggies(); // 蔬菜

    public Pepperoni createPepperoni(); // 香肠

    public Clams createClams(); // 蛤蜊类

}
