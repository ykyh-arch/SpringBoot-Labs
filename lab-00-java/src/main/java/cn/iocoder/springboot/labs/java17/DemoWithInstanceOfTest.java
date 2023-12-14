package cn.iocoder.springboot.labs.java17;

/**
 * DemoWithInstanceOfTest - InstanceOf 模式匹配
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithInstanceOfTest {

    public static void main(String[] args) {
        GrapeClass grapeClass = new GrapeClass();
        oldStyle(grapeClass);
    }

    private static void oldStyle(Object o) {
        // 常规方式
        if (o instanceof Furit) {
            Furit furit = (GrapeClass) o;
            System.out.println("This furit is :" + furit.getName());
        }
        // 简写方式
        if (o instanceof Furit furit) {
            System.out.println("This furit is :" + furit.name);
        }
        // furit.getColor() 提交成立依赖于第一个条件
        if (o instanceof Furit furit && furit.getColor()== "red") {
            System.out.println("This furit is :" + furit.getName());
        }
    }
}

