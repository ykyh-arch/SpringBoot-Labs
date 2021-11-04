package cn.iocoder.springboot.lab80.oss.config;

import cn.iocoder.springboot.lab80.oss.properties.MinioProperties;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * MinioConfiguration 配置类
 *
 * @author Jaquez
 * @date 2021/11/01 18:47
 */
@Configuration
public class MinioConfiguration {

    @Autowired
    private MinioProperties minioProp;

    @Bean
    public MinioClient minioClient(){

        MinioClient client = MinioClient.builder().endpoint(minioProp.getEndpoint()).credentials(minioProp.getAccessKey(),minioProp.getSecretKey()).build();
        return client;
    }

}
