package cn.iocoder.springboot.lab82.demo.bridge;

import cn.iocoder.springboot.lab82.demo.bridge.color.Color;
import cn.iocoder.springboot.lab82.demo.bridge.color.White;
import cn.iocoder.springboot.lab82.demo.bridge.shape.Rectangle;
import cn.iocoder.springboot.lab82.demo.bridge.shape.Shape;
import cn.iocoder.springboot.lab82.demo.bridge.shape.Square;

/**
 * 客户端测试类
 *
 * @author jaquez
 * @date 2022/01/10 14:40
 **/
public class Client {

    public static void main(String[] args) {
        // 白色
        Color white = new White();
        // 正方形
        Shape square = new Square();
        // 白色的正方形
        square.setColor(white);
        square.draw();

        // 长方形
        Shape rectange = new Rectangle();
        rectange.setColor(white);
        rectange.draw();
    }

}
