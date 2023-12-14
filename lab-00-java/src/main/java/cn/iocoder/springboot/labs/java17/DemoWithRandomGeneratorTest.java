package cn.iocoder.springboot.labs.java17;

import java.util.random.RandomGenerator;

import static java.util.random.RandomGeneratorFactory.of;

/**
 * DemoWithRandomGeneratorTest - 增强型伪随机数生成器支持
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithRandomGeneratorTest {

    public static void main(String[] args) {
        // generateRandomNumbers();
        useDifferentAlgorithms();
    }

    public static void generateRandomNumbers() {
        RandomGenerator random = RandomGenerator.getDefault();
        System.out.println("A random boolean: " + random.nextBoolean());
        System.out.println("A random int: " + random.nextInt());
        System.out.println("A random long: " + random.nextLong());
        System.out.println("A random float: " + random.nextFloat());
        System.out.println("A random double: " + random.nextDouble());
        System.out.println("A random gaussian: " + random.nextGaussian());
    }

    public static void useDifferentAlgorithms() {
        // RandomGeneratorFactory类提供了很多方法来获取或者查询不同的PRNG算法
        RandomGenerator random1 = of("L32X64MixRandom").create();
        RandomGenerator random2 = of("L64X128MixRandom").create();
        RandomGenerator random3 = of("L128X256MixRandom").create();
        System.out.println("Using L32X64MixRandom: " + random1.nextInt());
        System.out.println("Using L64X128MixRandom: " + random2.nextInt());
        System.out.println("Using L128X256MixRandom: " + random3.nextInt());
    }

}
