package cn.iocoder.springboot.lab28.task.service;

import cn.iocoder.springboot.lab28.task.mapper.SysTaskDao;
import cn.iocoder.springboot.lab28.task.entity.SysTask;
import cn.iocoder.springboot.lab28.task.enums.JobStatusEnum;
import cn.iocoder.springboot.lab28.task.utils.QuartzManagerUtil;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现类
 *
 * @author jaquez
 * @date 2021/07/08 15:31
 **/
@Service
public class ScheduleJobService implements TaskService{

    @Autowired
    QuartzManagerUtil quartzManager;

    @Autowired
    SysTaskDao sysTaskDao;

    @Override
    public void initSchedule() throws SchedulerException {
        // 从数据库获取任务信息，获取执行中的任务
        List<SysTask> jobList = (List<SysTask>) sysTaskDao.list();
        for (SysTask task : jobList) {
            if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
                quartzManager.addJob(task);
            }
        }
    }
}
