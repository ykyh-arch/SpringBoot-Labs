package cn.iocoder.springboot.labs.java17;

import java.text.NumberFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * DemoWithNumberFormatTest - 精简数字格式化支持
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithNumberFormatTest {

    public static void main(String[] args) {

        // 在NumberFormat中添加了一个工厂方法，可以根据Unicode标准以紧凑的、人类可读的形式格式化数字
        NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, NumberFormat.Style.SHORT);
        System.out.println(fmt.format(1000));
        System.out.println(fmt.format(100000));
        System.out.println(fmt.format(1000000));

        fmt = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, NumberFormat.Style.LONG);
        System.out.println(fmt.format(1000));
        System.out.println(fmt.format(100000));
        System.out.println(fmt.format(1000000));
    }



}
