package cn.iocoder.springboot.lab74.batchdemo.entity;

import lombok.Data;


/**
 * 输出实体示例
 *
 * @author Jaquez
 * @date 2022/02/24 15:03
 */
@Data
public class ArticleDetail {

    private String title;

    private String content;

    private String eventOccurredTime;

    private String source;

    private String description;

}
