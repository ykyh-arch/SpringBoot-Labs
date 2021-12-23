package cn.iocoder.springboot.lab82.demo.abstractfactory;

/**
 * CheesePizza
 *
 * @author jaquez
 * @date 2021/12/23 14:27
 **/
public class CheesePizza extends Pizza {

    PizzaIngredientFactory ingredientFactory;

    /*
     * 要制作披萨必须要有制作披萨的原料，而这些原料是从原料工厂运来的
     */
    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
        prepare();
    }

    /*
     *  实现 prepare() 方法
     *
     *  prepare() 方法一步一步地创建芝士比萨，每当需要原料时，就跟工厂要
     */
    @Override
    void prepare() {
        System.out.println("Prepareing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
    }

}
