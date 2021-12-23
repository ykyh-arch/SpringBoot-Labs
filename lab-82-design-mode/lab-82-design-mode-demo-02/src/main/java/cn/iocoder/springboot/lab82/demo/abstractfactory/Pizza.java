package cn.iocoder.springboot.lab82.demo.abstractfactory;

import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.cheese.Cheese;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.clams.Clams;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.dough.Dough;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.pepperoni.Pepperoni;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.sauce.Sauce;
import cn.iocoder.springboot.lab82.demo.abstractfactory.ingredient.veggies.Veggies;

/**
 * 披萨类
 * <p>
 * 在这个披萨类里面，我们需要使用原料，其他方法保持不变，将 prepare() 方法声明为抽象，在这个方法中，我们需要收集披萨所需要的原料。
 *
 * @author jaquez
 * @date 2021/12/23 14:18
 **/
public abstract class Pizza {

    /*
     * 每个披萨都持有一组在准备时会用到的原料
     */
    String name;
    Dough dough;
    Sauce sauce;
    Veggies veggies[];
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clams;

    /*
     * prepare() 方法声明为抽象方法。在这个方法中，我们需要收集披萨所需要的原料，而这些原料都是来自原料工厂
     */
    abstract void prepare();

    void bake() {
        System.out.println("Bake for 25 munites at 350");
    }

    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
