package cn.iocoder.springboot.lab82.demo.factorymethod;

/**
 * 具体披萨类-芝加哥风格奶酪披萨
 *
 * @author jaquez
 * @date 2021/12/23 10:18
 **/
public class ChicagoStyleCheesePizza extends Pizza {

    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sause = "Plum Tomato Sauce";

        toppings.add("Shredded Mozzarella Cheese");
    }

    public void cut() {
        System.out.println("Cutting the Pizza into square slices");
    }
}
