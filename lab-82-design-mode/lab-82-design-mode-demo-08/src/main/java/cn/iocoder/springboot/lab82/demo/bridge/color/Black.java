package cn.iocoder.springboot.lab82.demo.bridge.color;

/**
 * 黑色类
 *
 * @author jaquez
 * @date 2022/01/10 14:39
 **/
public class Black implements Color{

    public void bepaint(String shape) {
        System.out.println("黑色的" + shape);
    }

}
