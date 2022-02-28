package cn.iocoder.springboot.lab74.batchdemo.service;

import org.quartz.SchedulerException;

/**
 * ScheduleJobService 任务调度服务层
 *
 * @author Jaquez
 * @date 2022/02/27 13:59
 */
public interface ScheduleJobService {

    void initSchedule() throws SchedulerException;
}
