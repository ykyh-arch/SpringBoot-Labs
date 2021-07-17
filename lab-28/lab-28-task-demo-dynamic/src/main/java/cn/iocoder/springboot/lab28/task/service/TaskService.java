package cn.iocoder.springboot.lab28.task.service;


import cn.iocoder.springboot.lab28.task.entity.SysTask;

import java.util.List;

public interface TaskService {
    List<SysTask> initSchedule();
}
