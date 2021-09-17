package cn.iocoder.springboot.lab45.apollodemo.listener;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 利用 Apollo 动态调整线上日志级别，参考：http://www.kailing.pub/article/index/arcid/189.html
 * @author Jaquez
 * @date 2021/09/17 15:45
 */
@Component
public class LoggingSystemConfigListener {

    /**
     * 日志配置项的前缀
     */
    private static final String LOGGER_TAG = "logging.level.";

    // springboot 提供的日志系统，方便动态控制日志相关信息
    @Resource
    private LoggingSystem loggingSystem;

    // 获取本地缓存的 Apollo 配置
    @ApolloConfig
    private Config config;

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent changeEvent) throws Exception {
        // 获得 Apollo 所有配置项
        Set<String> keys = config.getPropertyNames();
        // 遍历配置集的每个配置项，判断是否是 logging.level 配置项
        for (String key : keys) {
            // 如果是 logging.level 配置项，则设置其对应的日志级别
            if (key.startsWith(LOGGER_TAG)) {
                // 获得日志级别
                String strLevel = config.getProperty(key, "info");
                LogLevel level = LogLevel.valueOf(strLevel.toUpperCase());
                // 设置日志级别到 LoggingSystem 中
                loggingSystem.setLogLevel(key.replace(LOGGER_TAG, ""), level);
            }
        }
    }

}
