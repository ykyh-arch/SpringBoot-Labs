package cn.iocoder.springboot.lab28.job.service;

import cn.iocoder.springboot.lab28.job.model.XxlJobInfo;

import java.util.List;

public interface JobInfoService {

    List<XxlJobInfo> getJobInfo(Integer jobGroupId, String executorHandler);

    Integer addJobInfo(XxlJobInfo xxlJobInfo);

}
