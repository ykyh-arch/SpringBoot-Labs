package cn.iocoder.springboot.lab82.demo.bridge.color;

/**
 * 白色
 *
 * @author jaquez
 * @date 2022/01/10 14:38
 **/
public class White implements Color{

    @Override
    public void bepaint(String shape) {
        System.out.println("白色的" + shape);
    }
}
