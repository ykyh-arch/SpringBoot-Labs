package cn.iocoder.springboot.lab82.demo.builder;

/**
 * 套餐构建者
 * <p>
 * 参考：https://www.iocoder.cn/DesignPattern/xiaomingge/Builder-Pattern/
 *
 * @author jaquez
 * @date 2021/12/23 15:53
 **/
public abstract class MealBuilder {

    Meal meal = new Meal();

    public abstract void buildFood();

    public abstract void buildDrink();

    public Meal getMeal(){
        return meal;
    }

}
