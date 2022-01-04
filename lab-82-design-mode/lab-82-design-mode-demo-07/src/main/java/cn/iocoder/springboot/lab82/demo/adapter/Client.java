package cn.iocoder.springboot.lab82.demo.adapter;

import cn.iocoder.springboot.lab82.demo.adapter.dog.Dog;
import cn.iocoder.springboot.lab82.demo.adapter.dog.DogAdapter;

/**
 * 客户端测试类，对象适配器
 *
 * 角色解释：target：目标接口（Robot）、adapter：适配器类（DogAdapter）、
 * adaptee：适配者（BioRobot）
 * 被适配者：（Dog）
 *
 *
 * @author jaquez
 * @date 2022/01/04 14:18
 **/
public class Client {

    public static void main(String[] args) {
        BioRobot robot = new BioRobot();  // 首先我们需要一个机器人
        Dog dog = new Dog();         // 和一只狗

        // 将这只狗包装到机器人中，使其有点儿像机器人
        Robot dogRobot = new DogAdapter(dog);

        //  然后是机器人叫和跑
        System.out.println("BioRob cry.....");
        dogRobot.cry();
        dogRobot.move();
    }

}
