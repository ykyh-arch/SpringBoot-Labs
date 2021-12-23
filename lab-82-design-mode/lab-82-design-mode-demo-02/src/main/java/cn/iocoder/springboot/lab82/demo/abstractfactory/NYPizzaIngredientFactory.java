package cn.iocoder.springboot.lab82.demo.abstractfactory;

import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.cheese.Cheese;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.cheese.ReggianoCheese;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.clams.Clams;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.clams.FreshClams;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.dough.Dough;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.dough.ThinCrustDough;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.pepperoni.Pepperoni;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.pepperoni.SlicedPepperoni;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.sauce.MarinaraSauce;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.sauce.Sauce;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.veggies.*;

/**
 * 纽约披萨原料工厂类
 *
 * @author jaquez
 * @date 2021/12/23 11:40
 **/
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Veggies[] createVeggies() {
        Veggies veggies[] = {new Garlic(), new Onion(), new Mushroom(), new RefPepper()};
        return veggies;
    }
}
