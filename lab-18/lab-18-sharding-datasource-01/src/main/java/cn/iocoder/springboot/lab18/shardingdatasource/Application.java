package cn.iocoder.springboot.lab18.shardingdatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ShardingSphere 分库分表，参考：https://mp.weixin.qq.com/s/fE29Ayn1lAW2ld0EwN0Ypg
 *
 * @author Jaquez
 * @date 2022/09/19 17:04
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.iocoder.springboot.lab18.shardingdatasource.mapper")
public class Application {
}
