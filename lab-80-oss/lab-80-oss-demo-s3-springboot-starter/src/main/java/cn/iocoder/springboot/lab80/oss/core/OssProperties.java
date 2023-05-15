package cn.iocoder.springboot.lab80.oss.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OssProperties 公共属性配置类
 *
 **/
@Data
@ConfigurationProperties(prefix = "oss")
public class OssProperties {

    /**
     * 对象存储服务的 URL
     */
    private String endpoint;

    /**
     * 区域
     */
    private String region;

    /**
     * true path-style nginx 反向代理和 S3 默认支持 pathStyle 模式 {http://endpoint/bucketname}
     * false supports virtual-hosted-style 阿里云等需要配置为 virtual-hosted-style 模式 {http://bucketname.endpoint}
     * 只是 url 的显示不一样
     */
    private Boolean pathStyleAccess = true;

    /**
     * Access key
     */
    private String accessKey;

    /**
     * Secret key
     */
    private String secretKey;

    /**
     * 最大线程数，默认： 100
     */
    private Integer maxConnections = 100;
}
