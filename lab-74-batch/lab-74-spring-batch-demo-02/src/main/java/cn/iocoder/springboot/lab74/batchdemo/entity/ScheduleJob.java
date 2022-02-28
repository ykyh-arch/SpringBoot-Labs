package cn.iocoder.springboot.lab74.batchdemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务类
 *
 * @author jaquez
 * @date 2021/07/08 15:49
 **/
@Data
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;
    // 主键ID
    private Long id;
    // 任务名
    private String jobName;
    // 任务描述
    private String description;
    // cron表达式
    private String cronExpression;
    // 任务执行时调用哪个类的方法，包名+类名
    private String beanClass;
    // 任务状态
    private String jobStatus;
    // 任务分组
    private String jobGroup;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

}
