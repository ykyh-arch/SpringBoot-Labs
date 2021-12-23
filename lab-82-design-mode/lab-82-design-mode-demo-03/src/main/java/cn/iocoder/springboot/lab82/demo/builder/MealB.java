package cn.iocoder.springboot.lab82.demo.builder;

/**
 * B 套餐
 *
 * @author jaquez
 * @date 2021/12/23 16:12
 **/
public class MealB extends MealBuilder{

    public void buildDrink() {
        meal.setDrink("一杯柠檬果汁");
    }

    public void buildFood() {
        meal.setFood("三个鸡翅");
    }
}
