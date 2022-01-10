package cn.iocoder.springboot.lab82.demo.bridge.shape;

import cn.iocoder.springboot.lab82.demo.bridge.color.Color;

/**
 * 形状类
 *
 * @author jaquez
 * @date 2022/01/10 14:29
 **/
public abstract class Shape {

    Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw();

}
