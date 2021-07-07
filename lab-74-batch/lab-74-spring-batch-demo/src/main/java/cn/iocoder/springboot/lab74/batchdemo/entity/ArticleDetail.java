package cn.iocoder.springboot.lab74.batchdemo.entity;

import lombok.Data;

/**
 * 输出实体数据结构
 */
@Data
public class ArticleDetail {

    private String title;

    private String content;

    private String eventOccurredTime;

    private String source;

    private String description;

}
