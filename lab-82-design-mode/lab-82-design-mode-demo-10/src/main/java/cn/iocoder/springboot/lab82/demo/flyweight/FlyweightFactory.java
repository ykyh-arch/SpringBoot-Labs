package cn.iocoder.springboot.lab82.demo.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂类
 *
 * 场景：假如我们有一个绘图的应用程序，通过它我们可以出绘制各种各样的形状、颜色的图形，那么这里形状和颜色就是内部状态了，通过享元模式我们就可以实现该属性的共享了。
 *
 * @author jaquez
 * @date 2022/01/19 11:08
 **/
public class FlyweightFactory {

    static Map<String, Shape> shapes = new HashMap<String, Shape>();

    public static Shape getShape(String key){
        Shape shape = shapes.get(key);
        // 如果 shape==null，表示不存在，则新建，并且保持到共享池中
        if(shape == null){
            shape = new Circle(key);
            shapes.put(key, shape);
        }
        return shape;
    }

    public static int getSum(){
        return shapes.size();
    }

}
