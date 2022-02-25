package cn.iocoder.springboot.lab74.batchdemo.utils;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * quartz 任务管理工具类，
 * - Group 统一使用 quartz 默认的组
 *
 * @author Jaquez
 * @date 2022/02/24 16:54
 */
@Component
@Slf4j
public class QuartzUtils {

   @Autowired
   private Scheduler scheduler;

   /**
    * 添加任务，SimpleScheduler
    *
    * @param jobClass jobClass
    * @param taskName jobName、triggerName 使用同一个 name
    * @param intervalTime 间隔时间, 单位：秒
    */
   public void addSingleJob(Class<? extends Job> jobClass, String taskName, int intervalTime) throws SchedulerException {
      JobDetail jobDetail = JobBuilder.newJob(jobClass)
            .withIdentity(taskName).build();
      SimpleScheduleBuilder simpleScheduler = SimpleScheduleBuilder.simpleSchedule()
            .withMisfireHandlingInstructionNextWithRemainingCount();
      // 触发器设置
      Trigger trigger = TriggerBuilder.newTrigger().startAt(new Date(System.currentTimeMillis() + 1000 * 10)).withIdentity(taskName)
            .withSchedule(
                  simpleScheduler.withIntervalInSeconds(intervalTime).repeatForever())
            .build();
      scheduler.scheduleJob(jobDetail, trigger);
   }

   /**
    * 添加任务，cron
    * @param jobClass jobClass
    * @param taskName jobName、triggerName使用同一个name
    * @param cron cron定时任务规则
    */
   public void addCronJob(Class<? extends Job> jobClass, String taskName, String cron) throws SchedulerException {
      JobDetail jobDetail = JobBuilder.newJob(jobClass)
            .withIdentity(taskName).build();
      // 基于Cron表达式的任务调度
      CronScheduleBuilder cronScheduler = CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing();
      Trigger trigger = TriggerBuilder.newTrigger().startNow().withIdentity(taskName)
            .withSchedule(cronScheduler)
            .build();
      scheduler.scheduleJob(jobDetail, trigger);
   }

   /**
    * 修改 cron 规则
    *
    * @author Jaquez
    * @date 2022/02/24 17:05
    * @param taskName
    * @param cron
    * @return void
    */
   public void modifyCron(String taskName, String cron) throws SchedulerException {
      TriggerKey triggerKey = TriggerKey.triggerKey(taskName);
      CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
      String oldTime = trigger.getCronExpression();
      if(!oldTime.equalsIgnoreCase(cron)){
         CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(cron);
         TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
         triggerBuilder.withIdentity(taskName).startNow().withSchedule(cronBuilder);
         trigger = (CronTrigger) triggerBuilder.build();
         // 重启启动 Job，使用新的 cron 规则
         scheduler.rescheduleJob(triggerKey, trigger);
      }
   }

   /**
    * 删除任务
    *
    * @author Jaquez
    * @date 2022/02/24 17:06
    * @param taskName
    * @return void
    */
   public void delJob(String taskName) throws SchedulerException {
      TriggerKey triggerKey = TriggerKey.triggerKey(taskName);
      JobKey jobKey = JobKey.jobKey(taskName);
      scheduler.pauseTrigger(triggerKey);
      scheduler.unscheduleJob(triggerKey);
      scheduler.deleteJob(jobKey);
   }
}