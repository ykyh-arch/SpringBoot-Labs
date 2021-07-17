package cn.iocoder.springboot.lab28.task.config;

import cn.iocoder.springboot.lab28.task.entity.SysTask;
import cn.iocoder.springboot.lab28.task.service.TaskService;
import cn.iocoder.springboot.lab28.task.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * 配置类
 *
 * @author jaquez
 * @date 2021/07/10 21:40
 **/
@EnableScheduling
@Configuration
public class ConfigurerScheduling  implements SchedulingConfigurer {
    //记录日志
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TaskService scheduleJobService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        List<SysTask> tasks = getAllTasks();
        if (tasks.size() > 0) {
            for (SysTask sysTask:tasks) {
                try {
                    taskRegistrar.addTriggerTask(getRunnable(sysTask), getTrigger(sysTask));
                } catch (Exception e) {
                    StackTraceElement[] stackTraceElements = e.getStackTrace();
                    logger.error(stackTraceElements[0].getClassName() + "类调用" + stackTraceElements[0].getMethodName() + "方法时在第" + stackTraceElements[0].getLineNumber() + "行发生异常！");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从数据库里取CRON任务信息
     * @author guoyukai
     * @createTime 2021/4/14 9:05
     * @params
     * @return java.util.List
     **/
    private List<SysTask> getAllTasks() {
        // 从数据库获取任务信息，获取执行中的任务
        List<SysTask> jobList = scheduleJobService.initSchedule();
        return jobList;
    }

    /**
     * 执行业务逻辑，反射执行
     * @author guoyukai
     * @createTime 2021/4/14 9:20
     * @params interfaceTask
     * @return java.lang.Runnable
     **/
    private Runnable getRunnable(SysTask sysTask) {
        return new Runnable() {

            @Override
            public void run() {
                try {
                    Object obj = SpringUtils.getBean(Class.forName(sysTask.getBeanClass()));
                    Method method = obj.getClass().getMethod(sysTask.getBeanMethod(),null);
                    method.invoke(obj);
                } catch (Exception e) {
                    StackTraceElement[] stackTraceElements = e.getStackTrace();
                    logger.error(stackTraceElements[0].getClassName() + "类调用" + stackTraceElements[0].getMethodName() + "方法时在第" + stackTraceElements[0].getLineNumber() + "行发生异常！");
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 获取下次执行时间
     * @author guoyukai
     * @createTime 2021/4/14 9:20
     * @params interfaceTask
     * @return org.springframework.scheduling.Trigger
     **/
    public Trigger getTrigger(SysTask sysTask) {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                //获取Cron表达式信息
                String cron = "";
                if (sysTask != null && sysTask.getCronExpression() != null) {
                    cron = sysTask.getCronExpression() ;
                }
                if (cron == null || "".equals(cron)) {
                    return null;
                }
                CronTrigger cronTrigger = new CronTrigger(cron);
                Date nextExec = cronTrigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        };
    }

}
