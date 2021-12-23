package cn.iocoder.springboot.lab82.demo.simplefactory;

/**
 * 披萨店
 *
 * @author jaquez
 * @date 2021/12/22 17:47
 **/
public class PizzaStore {

    SimplyPizzaFactory factory;  // SimplePizzaFactory 的引用

    public PizzaStore(SimplyPizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {

        Pizza pizza;
        // 使用工厂对象的创建方法，而不是直接 new。这里不再使用具体实例化
        pizza = factory.createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    public static void main(String[] args) {
        PizzaStore pizzaStore =new PizzaStore(new SimplyPizzaFactory());
        System.out.println(pizzaStore.orderPizza("cheese"));
    }
}
