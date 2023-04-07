package cn.iocoder.springboot.lab04.rabbitmqdemo.listener;

import cn.hutool.log.StaticLog;
import cn.iocoder.springboot.lab04.rabbitmqdemo.canal.CanalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * ApplicationReadyListener 应用完成监听器，参考：https://www.cnblogs.com/javastack/p/14138193.html
 *
 * @author jaquez
 * @date 2023/04/06 14:28
 **/
@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CanalClient canalClient;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        StaticLog.info("应用启动完成，正在监听 canal bin-log 数据同步信息…… ");
        // canalClient.start();
    }
}
