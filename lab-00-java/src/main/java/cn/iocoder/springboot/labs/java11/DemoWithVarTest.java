package cn.iocoder.springboot.labs.java11;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DemoWithVarTest - Lambda 中使用 var
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithVarTest {

    public static void main(String[] args) {
        myMethod();
    }

    public static void myMethod(){
        // (@NonNull var value1, @Nullable var value2) -> value1 + value2
        List<String> tutorialsList = Arrays.asList("Java", "HTML");

        String tutorials = tutorialsList.stream()
                .map((@NonNull var tutorial) -> tutorial.toUpperCase())
                .collect(Collectors.joining(", "));

        System.out.println(tutorials);
    }
}

@interface NonNull {}
