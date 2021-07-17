package cn.iocoder.springboot.lab28.task.service;

import cn.iocoder.springboot.lab28.task.dao.SysTaskDao;
import cn.iocoder.springboot.lab28.task.entity.SysTask;
import cn.iocoder.springboot.lab28.task.enums.JobStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现类
 *
 * @author jaquez
 * @date 2021/07/08 15:31
 **/
@Service
public class TaskJobService implements TaskService{

    @Autowired
    SysTaskDao sysTaskDao;

    @Override
    public List<SysTask> initSchedule()  {
        // 从数据库获取任务信息，获取启用中的任务
        List<SysTask> jobList = (List<SysTask>) sysTaskDao.list();
        List<SysTask> runningjobList = new ArrayList<>();
        for (SysTask task : jobList) {
            if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
                runningjobList.add(task);
            }
        }
        return runningjobList;
    }
}
