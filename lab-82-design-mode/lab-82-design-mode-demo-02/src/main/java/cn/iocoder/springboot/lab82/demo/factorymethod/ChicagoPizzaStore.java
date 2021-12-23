package cn.iocoder.springboot.lab82.demo.factorymethod;

/**
 * 披萨分店-芝加哥
 *
 * @author jaquez
 * @date 2021/12/23 10:27
 **/
public class ChicagoPizzaStore extends PizzaStoreFactory {

    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        if ("cheese".equals(type)) {
            pizza = new ChicagoStyleCheesePizza();
        } else if ("clam".equals(type)) {
            // pizza = new ChicagoStyleClamPizza();
        } else if ("pepperoni".equals(type)) {
            // pizza = new ChicagoStylePepperoniPizza();
        } else if ("veggie".equals(type)) {
            // pizza = new ChicagoStyleVeggiePizza();
        }
        return pizza;
    }
}
