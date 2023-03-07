package cn.iocoder.springboot.lab28.job.service;

import cn.iocoder.springboot.lab28.job.model.XxlJobGroup;

import java.util.List;

public interface JobGroupService {

    List<XxlJobGroup> getJobGroup();

    boolean autoRegisterGroup();

    boolean preciselyCheck();

}
