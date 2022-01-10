package cn.iocoder.springboot.lab82.demo.bridge.shape;

import cn.iocoder.springboot.lab82.demo.bridge.shape.Shape;

/**
 * 圆形类
 *
 * @author jaquez
 * @date 2022/01/10 14:33
 **/
public class Circle extends Shape {

    @Override
    public void draw() {
        color.bepaint("圆形");
    }
}
