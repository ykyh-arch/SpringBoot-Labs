package cn.iocoder.springboot.lab47.demo.service;

import cn.iocoder.springboot.lab47.demo.spi.Printer;

/**
 * 实现类1
 *
 * @author jaquez
 * @date 2021/07/17 19:36
 **/
public class GoodPrinter implements Printer {
    @Override
    public void print() {
        System.out.println("你是一个好人❤");
    }
}
