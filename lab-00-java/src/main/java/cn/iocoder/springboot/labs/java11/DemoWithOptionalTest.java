package cn.iocoder.springboot.labs.java11;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

/**
 * DemoWithOptionalTest - Optional 类
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithOptionalTest {

    public static void main(String[] args) {
        myMethod();
    }

    public static void myMethod(){
        // 可以用作 isPresent() 方法的替代方法，该方法通常需要否定以检查值是否不存在。
        String name = null;

        System.out.println(!Optional.ofNullable(name).isPresent());
        System.out.println(Optional.ofNullable(name).isEmpty());

        name = "Joe";
        System.out.println(!Optional.ofNullable(name).isPresent());
        System.out.println(Optional.ofNullable(name).isEmpty());
    }
}
