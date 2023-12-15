package cn.iocoder.springboot.labs.java11;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * DemoWithPredicateTest - Predicate 接口引入了新方法 not() 来否定类似于 negate 方法的现有谓词。
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithPredicateTest {

    public static void main(String[] args) {
        myMethod();
    }

    public static void myMethod(){
        List<String> tutorialsList = Arrays.asList("Java", "\n", "HTML", " ");

        List<String> tutorials = tutorialsList.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());

        tutorials.forEach(tutorial -> System.out.println(tutorial));
    }
}
