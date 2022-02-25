package cn.iocoder.springboot.lab74.batchdemo.entity;

import lombok.Data;

/**
 * 输入实体示例
 *
 * @author Jaquez
 * @date 2022/02/24 15:02
 */
@Data
public class Article {

    private String id;

    private String title;

    private String content;

    private String eventOccurredTime;

}
