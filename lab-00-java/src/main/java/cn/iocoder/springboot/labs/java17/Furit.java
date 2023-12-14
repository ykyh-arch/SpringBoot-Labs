package cn.iocoder.springboot.labs.java17;

import lombok.Data;

@Data
public abstract sealed class Furit permits Apple, GrapeClass, Pear { // 通过permits可以指定 Apple,Pear 类可以进行继承扩展

    public String name;
    private String color;

}
