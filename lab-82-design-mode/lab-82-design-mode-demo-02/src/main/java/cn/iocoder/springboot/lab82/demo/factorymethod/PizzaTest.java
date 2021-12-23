package cn.iocoder.springboot.lab82.demo.factorymethod;

/**
 * 披萨测试类
 *
 * @author jaquez
 * @date 2021/12/23 10:30
 **/
public class PizzaTest {

    public static void main(String[] args) {

        System.out.println("---------Joel 需要的芝加哥的奶酪披萨---------");
        ChicagoPizzaStore chicagoPizzaStore = new ChicagoPizzaStore(); // 建立芝加哥的披萨店
        Pizza joelPizza = chicagoPizzaStore.orderPizza("cheese"); // 下订单
        System.out.println("Joel ordered a " + joelPizza.getName() + "\n");

        System.out.println("---------Ethan 需要的纽约风味的披萨---------");
        NYPizzaStore nyPizzaStore = new NYPizzaStore();
        Pizza ethanPizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + ethanPizza.getName() + "\n");
    }

}
