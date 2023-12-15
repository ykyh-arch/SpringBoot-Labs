package cn.iocoder.springboot.labs.java11;

import java.util.ArrayList;
import java.util.List;

/**
 * DemoWithStringApiTest - String api 增强，单个 Java 文件而无需编译免编译启动
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithStringApiTest {

    public static void main(String[] args) {
        // 免编译启动，如：java ApiTester.java
        myMethod();
    }

    public static void myMethod(){
        String sample = " abc ";
        // 重复给定次数的字符串，返回连接的字符串。
        System.out.println(sample.repeat(2)); // " abc  abc "
        // 检查字符串是否为空或只有空格。
        System.out.println(sample.isBlank()); // false
        System.out.println("".isBlank()); // true
        System.out.println("   ".isBlank()); // true
        //  删除前导和尾随空格。
        System.out.println(sample.strip()); // "abc"
        // 删除前导空格。
        System.out.println(sample.stripLeading()); // "abc "
        //  删除尾随空格。
        System.out.println(sample.stripTrailing()); // " abc"
        // 返回多行字符串的行流。
        sample = "This\nis\na\nmultiline\ntext.";
        List<String> lines = new ArrayList<>();
        sample.lines().forEach(line -> lines.add(line));
        lines.forEach(line -> System.out.println(line));
    }
}
