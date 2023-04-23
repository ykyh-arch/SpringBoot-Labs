package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.datasource.driver;

import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.entity.DataSource;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class DriverFactoryManager {

    private final List<DriverFactory> factories;

    public DriverFactory findUsableDriverFactory(DataSource config) {
        for (DriverFactory factory : factories) {
            if (factory.usable(config)) {
                return factory;
            }
        }
        throw ExceptionUtils.mpe("找不到数据源驱动: " + config.getJdbcUrl() + ", 请联系管理员适配");
    }
}
