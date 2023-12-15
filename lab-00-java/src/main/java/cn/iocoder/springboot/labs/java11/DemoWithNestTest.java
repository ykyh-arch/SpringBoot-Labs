package cn.iocoder.springboot.labs.java11;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DemoWithNestTest - 嵌套类
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithNestTest {

    public static void main(String[] args) {
        myMethod();
    }

    public static void myMethod(){
        boolean isNestMate = DemoWithNestTest.class.isNestmateOf(DemoWithNestTest.Inner.class);
        boolean nestHost = DemoWithNestTest.Inner.class.getNestHost() == DemoWithNestTest.class;

        System.out.println(isNestMate);
        System.out.println(nestHost);

        Set<String> nestedMembers = Arrays.stream(DemoWithNestTest.Inner.class.getNestMembers())
                .map(Class::getName)
                .collect(Collectors.toSet());
        System.out.println(nestedMembers);
    }

    public static class Inner {
    }
}
