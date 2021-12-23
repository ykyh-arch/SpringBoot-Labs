package cn.iocoder.springboot.lab82.demo.factorymethod;

/**
 * 披萨分店-纽约
 *
 * @author jaquez
 * @date 2021/12/23 10:24
 **/
public class NYPizzaStore extends PizzaStoreFactory {

    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        if ("cheese".equals(type)) {
            pizza = new NYStyleCheesePizza();
        } else if ("veggie".equals(type)) {
            // pizza = new NYStyleVeggiePizza();
        } else if ("clam".equals(type)) {
            // pizza = new NYStyleClamPizza();
        } else if ("pepperoni".equals(type)) {
            // pizza = new NYStylePepperoniPizza();
        }

        return pizza;
    }
}
