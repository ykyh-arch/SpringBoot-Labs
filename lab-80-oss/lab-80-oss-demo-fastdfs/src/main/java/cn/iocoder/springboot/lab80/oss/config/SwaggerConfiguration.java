package cn.iocoder.springboot.lab80.oss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 因为 Spring Boot 暂未提供 Swagger 内置的支持，所以我们需要自己定义配置类。
 *
 * @author Jaquez
 * @date 2021/12/10 10:16
 */
@Configuration
@EnableSwagger2 // 标记项目启用 Swagger API 接口文档
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        // 创建 Docket 对象
        return new Docket(DocumentationType.SWAGGER_2) // 文档类型，使用 Swagger2
                .apiInfo(this.apiInfo()) // 设置 API 信息
                // 扫描 Controller 包路径，获得 API 接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.iocoder.springboot.lab80.oss.controller"))
                .paths(PathSelectors.any())
                // 构建出 Docket 对象
                .build();
    }

    /**
     * 创建 API 信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("fastdfs 接口文档")
                .description("FastDFS是一个开源的分布式文件系统，她对文件进行管理，功能包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，解决了大容量存储和负载均衡的问题。特别适合以文件为载体的在线服务，如相册网站、视频网站等等。")
                .version("1.0.0") // 版本号
                .licenseUrl("https://www.oschina.net/p/fastdfs")
                .contact(new Contact("Jaquez", "https://www.oschina.net/p/fastdfs", "jaquez121314@gmail.com")) // 联系人
                .build();
    }

}
