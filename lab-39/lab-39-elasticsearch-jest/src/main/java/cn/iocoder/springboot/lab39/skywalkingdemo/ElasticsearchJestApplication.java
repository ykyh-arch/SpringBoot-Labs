package cn.iocoder.springboot.lab39.skywalkingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

/**
 * 入口类
 *
 * @author Jaquez
 * @date 2021/12/21 15:50
 */
@SpringBootApplication(exclude = {ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
public class ElasticsearchJestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchJestApplication.class, args);
    }

}
