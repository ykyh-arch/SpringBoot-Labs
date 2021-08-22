package cn.iocoder.springboot.lab15.springdatajest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

/**
 * ES 示例
 * Elasticsearch（ES）提供了两种连接方式：
 * 通过 TCP 方式访问 ES 。
 * 通过 HTTP API 方式访问 ES 。
 * @author Jaquez
 * @date 2021/08/19 10:48
 */
// 排除 ElasticsearchAutoConfiguration 和 ElasticsearchDataAutoConfiguration 自动配置类，否则会自动配置 Spring Data Elasticsearch 。
@SpringBootApplication(exclude = {ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
public class Application {
}
