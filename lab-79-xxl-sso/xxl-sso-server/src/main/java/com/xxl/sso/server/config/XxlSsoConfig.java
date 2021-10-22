package com.xxl.sso.server.config;

import com.xxl.sso.core.store.SsoLoginStore;
import com.xxl.sso.core.util.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 认证服务配置类，作用：初始化化缓存（本地缓存、分布式 redis 缓存）
 *
 * @author xuxueli 2018-04-03 20:41:07
 */
@Configuration
public class XxlSsoConfig implements InitializingBean, DisposableBean {

    @Value("${xxl.sso.redis.address}")
    private String redisAddress;

    @Value("${xxl.sso.redis.expire.minute}")
    private int redisExpireMinute;

    @Override
    public void afterPropertiesSet() throws Exception {
        SsoLoginStore.setRedisExpireMinute(redisExpireMinute);
        JedisUtil.init(redisAddress);
    }

    @Override
    public void destroy() throws Exception {
        JedisUtil.close();
    }

}
