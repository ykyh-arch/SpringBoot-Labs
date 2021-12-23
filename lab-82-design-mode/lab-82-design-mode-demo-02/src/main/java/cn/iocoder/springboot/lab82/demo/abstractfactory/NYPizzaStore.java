package cn.iocoder.springboot.lab82.demo.abstractfactory;

/**
 * 纽约披萨店
 *
 * @author jaquez
 * @date 2021/12/23 15:02
 **/
public class NYPizzaStore extends PizzaStoreFactory {

    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        // 使用纽约的原料工厂
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
        if ("cheese".equals(type)) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        } else if ("veggie".equals(type)) {
            // pizza = new VeggiePizza(ingredientFactory);
            // pizza.setName("New York Style Veggie Pizza");
        } else if ("clam".equals(type)) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("New York Style Clam Pizza");
        } else if ("pepperoni".equals(type)) {
            // pizza = new PepperoniPizza(ingredientFactory);
            // pizza.setName("New York Style Pepperoni Pizza");
        }
        return pizza;
    }

}
