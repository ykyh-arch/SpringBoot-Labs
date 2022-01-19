package cn.iocoder.springboot.lab82.demo.flyweight;

/**
 * 圆形类
 *
 * @author jaquez
 * @date 2022/01/19 11:11
 **/
public class Circle extends Shape{

    private String color;

    public Circle(String color){
        this.color = color;
    }

    public void draw() {
        System.out.println("画了一个" + color +"的圆形");
    }
}
