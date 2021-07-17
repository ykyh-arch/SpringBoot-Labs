package cn.iocoder.springboot.lab47.demo.service;

import cn.iocoder.springboot.lab47.demo.spi.Printer;

/**
 * 实现类2
 *
 * @author jaquez
 * @date 2021/07/17 19:43
 **/
public class BadPrinter implements Printer {
    @Override
    public void print() {
        System.out.println("我抽烟，喝酒，蹦迪，但我知道我是好女孩~");
    }
}
