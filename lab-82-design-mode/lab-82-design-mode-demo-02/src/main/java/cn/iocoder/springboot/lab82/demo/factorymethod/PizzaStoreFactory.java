package cn.iocoder.springboot.lab82.demo.factorymethod;

/**
 * 抽象工厂-披萨总店
 *
 * 参考：https://www.iocoder.cn/DesignPattern/xiaomingge/Factory-Method/
 *
 * 在工厂方法模式中，核心工厂类不在负责产品的创建，而是将具体的创建工作交给子类去完成。也就是后所这个核心工厂仅仅只是提供创建的接口，具体实现方法交给继承它的子类去完成。当我们的系统需要增加其他新的对象时，我们只需要添加一个具体的产品和它的创建工厂即可，不需要对原工厂进行任何修改，这样很好地符合了“开闭原则”。
 *
 * 适用场景
 *
 * 在工厂方法模式中，我们不需要具体产品的类名，我们只需要知道创建它的具体工厂即可。
 * 在工厂方法模式中，我们使用一个工厂创建一个产品，也就是说一个具体的工厂对应一个具体的产品。
 *
 * @author jaquez
 * @date 2021/12/23 10:20
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

