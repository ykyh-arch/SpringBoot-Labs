package cn.iocoder.springboot.lab28.task.service;

import cn.iocoder.springboot.lab28.task.config.CronTaskRegistrar;
import cn.iocoder.springboot.lab28.task.config.SchedulingRunnable;
import cn.iocoder.springboot.lab28.task.entity.SysJob;
import cn.iocoder.springboot.lab28.task.enums.JobStatusEnum;
import cn.iocoder.springboot.lab28.task.mapper.SysJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 定时任务服务层
 *
 * @author jaquez
 * @date 2021/08/26 15:56
 **/
@Service
public class SysJobService {

    @Autowired
    SysJobMapper sysJobMapper;

    @Autowired
    CronTaskRegistrar cronTaskRegistrar;

    // 添加定时任务，添加即运行
    public String insert(SysJob sysJob){

        int num = sysJobMapper.insert(sysJob);
        if (num < 0) {
            return "tianjia dingshi renwu shibai";
        } else {
            if (sysJob.getJobStatus().equals(JobStatusEnum.NORAML.getCode())) {
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                cronTaskRegistrar.addCronTask(task, sysJob.getCronExpression());
            }
        }

        return "tianjia dingshi renwu chengong";
    }

    // 修改定时任务，先移除原来的任务，再启动新任务
    public String update(SysJob sysJob){
        SysJob existedSysJob = sysJobMapper.selectByPrimaryKey(sysJob.getJobId());
        int num = sysJobMapper.updateByPrimaryKeySelective(sysJob);

        if (num < 0) {
            return "xiugai dingshi renwu shibai";
        } else {
            // 先移除再添加
            if (existedSysJob.getJobStatus().equals(JobStatusEnum.NORAML.getCode())) {
                SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
                cronTaskRegistrar.removeCronTask(task);
            }

            if (sysJob.getJobStatus().equals(JobStatusEnum.NORAML.getCode())) {
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                cronTaskRegistrar.addCronTask(task, sysJob.getCronExpression());
            }

            return "xiugai dingshi renwu chengong";
        }
    }

    // 删除定时任务
    public String delete(Integer jobId){
        SysJob existedSysJob = sysJobMapper.selectByPrimaryKey(jobId);
        int num = sysJobMapper.deleteByPrimaryKey(jobId);
        if (num < 0) {
            return "shanchu dingshi renwu shibai";
        } else{
            if (existedSysJob.getJobStatus().equals(JobStatusEnum.NORAML.getCode())) {
                SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
                cronTaskRegistrar.removeCronTask(task);
            }
        }
        return "shanchu dingshi renwu chengong";
    }

    // 定时任务启动/停止状态切换

    public String startOrStop(Integer jobId){
        SysJob existedSysJob = sysJobMapper.selectByPrimaryKey(jobId);
        if (existedSysJob.getJobStatus().equals(JobStatusEnum.NORAML.getCode())) {
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            cronTaskRegistrar.addCronTask(task, existedSysJob.getCronExpression());
        } else {
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            cronTaskRegistrar.removeCronTask(task);
        }
        return "qidonghuotingzhi dingshi renwu chengong";
    }

}
