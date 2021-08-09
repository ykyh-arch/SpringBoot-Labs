package cn.iocoder.springboot.lab12.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Mybaits 快速生成代码插件参考：https://github.com/gejun123456/MyBatisCodeHelper-Pro 1.5K
 * https://github.com/zouzg/mybatis-generator-gui 5.9K
 * @author Jaquez
 * @date 2021/08/08 15:19
 */
@SpringBootApplication
// 实际开发中，建议包扫描放到配置类上
@MapperScan(basePackages = "cn.iocoder.springboot.lab12.mybatis.mapper")
public class Application {
}
