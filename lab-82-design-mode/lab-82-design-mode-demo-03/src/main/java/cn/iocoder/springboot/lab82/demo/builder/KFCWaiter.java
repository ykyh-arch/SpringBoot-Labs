package cn.iocoder.springboot.lab82.demo.builder;

/**
 * KFCWaiter KFC 服务员，相当于指挥者
 *
 * @author jaquez
 * @date 2021/12/23 16:13
 **/
public class KFCWaiter {

    private MealBuilder mealBuilder;

    public void setMealBuilder(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    // 准备套餐
    public Meal construct(){
        //准备食物
        mealBuilder.buildFood();
        //准备饮料
        mealBuilder.buildDrink();

        //准备完毕，返回一个完整的套餐给客户
        return mealBuilder.getMeal();
    }

}
