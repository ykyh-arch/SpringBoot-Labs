package cn.iocoder.springboot.lab01.springsecurity.service;

import cn.hutool.crypto.SecureUtil;

/**
 * 测试类
 *
 * @author jaquez
 * @date 2021/09/26 15:24
 **/
public class Test {

    public static void main(String[] args) {
        // c28cbca6caf7da4d83bd26c949cca9fa
        System.out.println(SecureUtil.md5("123456" + "_secret"));
    }
}
