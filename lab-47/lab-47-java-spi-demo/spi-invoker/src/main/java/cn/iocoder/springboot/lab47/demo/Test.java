package cn.iocoder.springboot.lab47.demo;

import cn.iocoder.springboot.lab47.demo.spi.Printer;

import java.sql.Driver;
import java.util.ServiceLoader;

/**
 * 测试类
 * 参考：https://www.iocoder.cn/Fight/xuma/spi/?self
 * @author jaquez
 * @date 2021/07/17 19:52
 **/
public class Test {
    public static void main(String[] args) {
        ServiceLoader<Printer> printerLoader = ServiceLoader.load(Printer.class);
        for (Printer printer : printerLoader) {
            printer.print();
        }
    }
//    com.mysql.jdbc.Driver
}
