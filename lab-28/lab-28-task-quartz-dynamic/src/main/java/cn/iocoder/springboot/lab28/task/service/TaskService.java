package cn.iocoder.springboot.lab28.task.service;

import org.quartz.SchedulerException;

public interface TaskService {
    void initSchedule() throws SchedulerException;
}
