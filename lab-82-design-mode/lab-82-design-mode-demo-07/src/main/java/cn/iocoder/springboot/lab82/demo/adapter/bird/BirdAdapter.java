package cn.iocoder.springboot.lab82.demo.adapter.bird;

import cn.iocoder.springboot.lab82.demo.adapter.Robot;

/**
 * 适配器类
 *
 * @author jaquez
 * @date 2022/01/04 14:30
 **/
public class BirdAdapter implements Robot {

    Bird bird;

    public BirdAdapter(Bird bird) {
        this.bird = bird;
    }

    public void cry() {
        bird.jiji();
    }

    public void move() {
        bird.fly();
    }
}
