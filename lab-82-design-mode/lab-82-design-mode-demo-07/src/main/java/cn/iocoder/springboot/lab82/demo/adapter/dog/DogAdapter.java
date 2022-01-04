package cn.iocoder.springboot.lab82.demo.adapter.dog;

import cn.iocoder.springboot.lab82.demo.adapter.Robot;

/**
 * 适配器类
 *
 * @author jaquez
 * @date 2022/01/04 14:15
 **/
public class DogAdapter implements Robot {

    Dog dog;

    public DogAdapter(Dog dog) { // 取得要适配的对象的引用
        this.dog = dog;
    }

    /*
     * 实现接口中的方法，只需要在相应的方法间进行转换即可完成。
     */
    public void cry() {
        System.out.println("机器人模拟狗叫...");
        dog.wang();
    }

    public void move() {
        System.out.println("机器人模拟狗跑...");
        dog.run();
    }

}
