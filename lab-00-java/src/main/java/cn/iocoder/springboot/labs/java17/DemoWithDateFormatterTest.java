package cn.iocoder.springboot.labs.java17;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * DemoWithDateFormatterTest - 日期周期格式化，Helpful NullPointerExceptions 空指针异常
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithDateFormatterTest {

    public static void main(String[] args) {

        // 根据 Unicode 标准指示一天时间段
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("B");
        System.out.println(dtf.format(LocalTime.of(8, 0)));
        System.out.println(dtf.format(LocalTime.of(13, 0)));
        System.out.println(dtf.format(LocalTime.of(20, 0)));
        System.out.println(dtf.format(LocalTime.of(23, 0)));
        System.out.println(dtf.format(LocalTime.of(0, 0)));
    }



}
