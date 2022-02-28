package cn.iocoder.springboot.lab74.batchdemo.service.impl;

import cn.iocoder.springboot.lab74.batchdemo.datasource.DataSource;
import cn.iocoder.springboot.lab74.batchdemo.entity.ScheduleJob;
import cn.iocoder.springboot.lab74.batchdemo.enums.DataSourceType;
import cn.iocoder.springboot.lab74.batchdemo.enums.JobStatusEnum;
import cn.iocoder.springboot.lab74.batchdemo.mapper.ScheduleJobMapper;
import cn.iocoder.springboot.lab74.batchdemo.utils.QuartzManagerUtil;
import cn.iocoder.springboot.lab74.batchdemo.service.ScheduleJobService;
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
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private QuartzManagerUtil quartzManager;

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Override
    @DataSource(value = DataSourceType.QUARTZ)
    public void initSchedule() throws SchedulerException {

        // 从数据库获取任务信息，获取执行中的任务
        List<ScheduleJob> jobList = scheduleJobMapper.getRunningJob(JobStatusEnum.RUNNING.getCode());

        jobList.forEach(job->{
            quartzManager.addJob(job);
        });
    }

}
