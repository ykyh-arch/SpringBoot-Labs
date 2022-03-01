package cn.iocoder.springboot.lab74.batchdemo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author
 * 文章
 */
@Data
public class Article implements Serializable {
    /**
     * 唯一键
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 事件发生时间
     */
    private String eventOccurredTime;

    private static final long serialVersionUID = 1L;
}
