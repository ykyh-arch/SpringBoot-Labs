package cn.iocoder.springboot.lab74.batchdemo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author
 * 文章详情
 */
@Data
public class ArticleDetail implements Serializable {
    /**
     * 文章ID
     */
    private String articleId;

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

    /**
     * 文章来源
     */
    private String source;

    /**
     * 描述信息
     */
    private String description;

    private static final long serialVersionUID = 1L;
}
