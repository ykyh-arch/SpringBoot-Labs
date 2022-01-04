package cn.iocoder.springboot.lab12.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用 mybatis 实现加密与解密的功能，参考自：https://mp.weixin.qq.com/s/Ise6E7i6Ffs3lOd8XAq25A
 *
 * @author Jaquez
 * @date 2021/08/08 15:19
 */
@SpringBootApplication
// 实际开发中，建议包扫描放到配置类上
@MapperScan(basePackages = "cn.iocoder.springboot.lab12.mybatis.mapper")
public class Application {

}
