package cn.iocoder.springboot.lab38.elkdemo.utils;

import org.slf4j.MDC;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 用以获取日志中的[%X{hostName}]、[%X{ip}]、[%X{applicationName}]三个字段值并赋值到日志里面去
 *
 * @author jaquez
 * @date 2021/08/23 16:19
 **/
@Component
public class InputMDC implements EnvironmentAware {

    private static Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        InputMDC.environment = environment;
    }

    public static void putMDC() {
        MDC.put("hostName", NetUtil.getLocalHostName());
        MDC.put("ip", NetUtil.getLocalIp());
        MDC.put("applicationName", environment.getProperty("spring.application.name"));
    }

}
