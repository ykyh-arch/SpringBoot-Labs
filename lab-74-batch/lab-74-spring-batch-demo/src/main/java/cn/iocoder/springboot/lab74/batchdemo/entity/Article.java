package cn.iocoder.springboot.lab74.batchdemo.entity;

import lombok.Data;

/**
 * 输入实体
 */
@Data
public class Article {

    private String title;

    private String content;

    private String eventOccurredTime;

}
