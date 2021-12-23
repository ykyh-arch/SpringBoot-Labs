package cn.iocoder.springboot.lab82.demo.abstractfactory;

/**
 * 披萨测试类
 *
 * @author jaquez
 * @date 2021/12/23 15:10
 **/
public class PizzaTest {

    public static void main(String[] args) {

        System.out.println("---------Ethan 需要的纽约风味的披萨---------");
        NYPizzaStore nyPizzaStore = new NYPizzaStore();
        Pizza ethanPizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + ethanPizza.getName() + "\n");
    }
}
