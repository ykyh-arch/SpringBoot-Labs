package cn.iocoder.springboot.lab75.activitidemo.dto;

import java.io.Serializable;

public class ModelerDto implements Serializable {


    /**
     * 模型id
     */
    private String id;


    /**
     * 模型名称
     */
    private String name;


    /**
     * 模型标识
     */
    private String key;


    /**
     * 模型分类
     */
    private String category;


    /**
     * 模型描述
     */
    private String description;

    /**
     * 模型版本
     */
    private String version;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
