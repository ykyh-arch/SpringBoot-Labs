package cn.iocoder.springboot.lab82.demo.abstractfactory;

/**
 * 披萨店抽象工厂类
 *
 * @author jaquez
 * @date 2021/12/23 14:56
 **/
public abstract class PizzaStoreFactory {

    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    /*
     * 创建 pizza 的方法交给子类去实现
     */
    abstract Pizza createPizza(String type);

}
