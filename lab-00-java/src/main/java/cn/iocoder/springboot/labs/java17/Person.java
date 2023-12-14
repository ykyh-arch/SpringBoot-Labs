package cn.iocoder.springboot.labs.java17;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Person
 *
 * @author fw001
 * @date 2023/12/14 14:21
 **/
@Data
@AllArgsConstructor
public class Person {

    private String name;

    private int age;

    private String address;
}
