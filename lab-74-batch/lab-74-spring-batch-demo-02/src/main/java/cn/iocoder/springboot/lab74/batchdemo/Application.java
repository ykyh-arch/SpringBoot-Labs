package cn.iocoder.springboot.lab74.batchdemo;

import cn.iocoder.springboot.lab74.batchdemo.service.ScheduleJobService;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * spring batch + spring quartz + 框架库与业务库分离版演示 Demo
 *
 * @author Jaquez
 * @date 2021/07/07 20:45
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 踩坑：排除默认的数据源自动配置，采用自定义数据源配置
@MapperScan(basePackages = "cn.iocoder.springboot.lab74.batchdemo.mapper")
public class Application implements ApplicationListener<ApplicationEvent> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ScheduleJobService scheduleJobService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // 监听事件
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ApplicationReadyEvent) {
            // 初始化调度逻辑
            try {
                scheduleJobService.initSchedule();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
            logger.info("系统服务{}完成启动了", ((ApplicationReadyEvent) applicationEvent).getSpringApplication());
        }
    }
}
