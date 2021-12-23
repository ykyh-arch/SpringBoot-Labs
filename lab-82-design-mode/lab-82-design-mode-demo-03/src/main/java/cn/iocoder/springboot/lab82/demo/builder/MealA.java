package cn.iocoder.springboot.lab82.demo.builder;

/**
 * A 套餐
 *
 * @author jaquez
 * @date 2021/12/23 16:11
 **/
public class MealA extends MealBuilder{

    public void buildDrink() {
        meal.setDrink("一杯可乐");
    }

    public void buildFood() {
        meal.setFood("一盒薯条");
    }

}
