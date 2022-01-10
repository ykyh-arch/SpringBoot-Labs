package cn.iocoder.springboot.lab82.demo.bridge.shape;

import cn.iocoder.springboot.lab82.demo.bridge.shape.Shape;

/**
 * 长方形
 *
 * @author jaquez
 * @date 2022/01/10 14:34
 **/
public class Rectangle extends Shape {

    @Override
    public void draw() {
        color.bepaint("长方形");
    }
}
