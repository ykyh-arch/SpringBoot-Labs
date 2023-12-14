package cn.iocoder.springboot.labs.java17;

/**
 * DemoWithSealedClassTest - 密封类 sealed class。
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithSealedClassTest {

    public static void main(String[] args) {
        Apple apple = new Apple();
        Pear pear = new Pear();
        // 可以将 apple 赋值给 Fruit
        Furit furit = apple;
        // 只能继承Apple，不能继承Furit
        class Avocado extends Apple {};
    }

}

