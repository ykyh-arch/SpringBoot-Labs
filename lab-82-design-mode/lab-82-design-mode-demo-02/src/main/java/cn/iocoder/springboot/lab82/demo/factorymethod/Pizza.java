package cn.iocoder.springboot.lab82.demo.factorymethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象披萨类
 *
 * @author jaquez
 * @date 2021/12/23 10:11
 **/
public class Pizza {

    protected String name;   // 名称
    protected String dough;  // 面团
    protected String sause;  // 酱料
    protected List<String> toppings = new ArrayList<String>();  // 佐料


    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough");
        System.out.println("Adding sause");
        System.out.println("Adding toppings");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println("  " + toppings.get(i));
        }
    }

    public void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }
}
