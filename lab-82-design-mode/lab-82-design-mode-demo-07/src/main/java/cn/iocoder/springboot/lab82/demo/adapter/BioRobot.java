package cn.iocoder.springboot.lab82.demo.adapter;

/**
 * 仿生机器人类
 *
 * @author jaquez
 * @date 2022/01/04 14:10
 **/
public class BioRobot implements Robot {

    public void cry() {
        System.out.println("仿生机器人叫.....");
    }

    public void move() {
        System.out.println("仿生机器人慢慢移动....");
    }
}
