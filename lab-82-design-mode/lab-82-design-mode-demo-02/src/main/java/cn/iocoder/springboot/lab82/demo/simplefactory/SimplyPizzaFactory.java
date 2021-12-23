package cn.iocoder.springboot.lab82.demo.simplefactory;

/**
 * 简单工厂类，参考：https://www.iocoder.cn/DesignPattern/xiaomingge/Simple-Factory-Pattern/
 * <p>
 * 简单工厂模式又称之为静态工厂方法，属于创建型模式。在简单工厂模式中，可以根据传递的参数不同，返回不同类的实例。简单工厂模式定义了一个类，这个类专门用于创建其他类的实例，这些被创建的类都有一个共同的父类。
 * <p>
 * 模式场景：在一个披萨店中，要根据不同客户的口味，生产不同的披萨，如素食披萨、希腊披萨等披萨。
 * <p>
 * 专门用于创建披萨的工厂类
 *
 * @author jaquez
 * @date 2021/12/22 17:33
 **/
public class SimplyPizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("clam")) {
            // pizza = new ClamPizza();
        } else if (type.equals("pepperoni")) {
            // pizza = new PepperoniPizza();
        } else if (type.equals("veggie")) {
            // pizza = new VeggiePizze();
        }

        return pizza;
    }

}
