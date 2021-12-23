package cn.iocoder.springboot.lab82.demo.factorymethod;

/**
 * 具体披萨类-纽约风格奶酪披萨
 *
 * @author jaquez
 * @date 2021/12/23 10:15
 **/
public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza() {
        name = "Ny Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sause = "Marinara Sauce";

        toppings.add("Crated Reggiano Cheese");
    }
}
