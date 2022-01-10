package cn.iocoder.springboot.lab82.demo.bridge.color;

/**
 * 灰色类
 *
 * @author jaquez
 * @date 2022/01/10 14:38
 **/
public class Gray implements Color{

    public void bepaint(String shape) {
        System.out.println("灰色的" + shape);
    }
}
