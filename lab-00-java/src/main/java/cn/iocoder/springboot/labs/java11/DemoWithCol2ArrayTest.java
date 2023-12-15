package cn.iocoder.springboot.labs.java11;

import java.util.Arrays;
import java.util.List;

/**
 * DemoWithCol2ArrayTest - 集合转为数组
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithCol2ArrayTest {

    public static void main(String[] args) {
        myMethod();
    }

    public static void myMethod(){
        // 旧方法
        // nameArray = nameList.toArray(new String[nameList.size()]);
        // 新方法
        // nameArray = nameList.toArray(String[]::new);

        List<String> namesList = Arrays.asList("Joe", "Julie");
        // Old way
        String[] names = namesList.toArray(new String[namesList.size()]);
        System.out.println(names.length);
        // New way
        names = namesList.toArray(String[]::new);
        System.out.println(names.length);
    }
}
