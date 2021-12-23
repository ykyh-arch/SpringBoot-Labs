package cn.iocoder.springboot.lab82.demo.builder;

/**
 * 套餐类
 *
 * @author jaquez
 * @date 2021/12/23 15:54
 **/
public class Meal {

    private String food;  // 食物
    private String drink; // 饮料

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

}
