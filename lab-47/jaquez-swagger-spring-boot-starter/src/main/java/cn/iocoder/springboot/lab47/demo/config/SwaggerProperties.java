package cn.iocoder.springboot.lab47.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * swagger 属性
 *
 * @author jaquez
 * @date 2021/07/17 15:44
 **/
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 版本
     */
    private String version;
    /**
     * 包名
     */
    private String basePackage;

    public String getTitle() {
        return title;
    }

    public SwaggerProperties setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SwaggerProperties setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public SwaggerProperties setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public SwaggerProperties setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

}
