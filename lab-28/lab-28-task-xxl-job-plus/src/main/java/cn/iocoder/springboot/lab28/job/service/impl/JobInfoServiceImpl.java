package cn.iocoder.springboot.lab28.job.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iocoder.springboot.lab28.job.model.XxlJobInfo;
import cn.iocoder.springboot.lab28.job.service.JobInfoService;
import cn.iocoder.springboot.lab28.job.service.JobLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JobInfo 实现类
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Autowired
    private JobLoginService jobLoginService;

    /**
     * 获取调度中心的任务列表
     * @param jobGroupId
     * @param executorHandler
     * @return
     */
    @Override
    public List<XxlJobInfo> getJobInfo(Integer jobGroupId,String executorHandler) {
        String url=adminAddresses+"/jobinfo/pageList";
        HttpResponse response = HttpRequest.post(url)
                .form("jobGroup", jobGroupId)
                .form("executorHandler", executorHandler)
                .form("triggerStatus", -1)
                .cookie(jobLoginService.getCookie())
                .execute();

        String body = response.body();
        JSONArray array = JSONUtil.parse(body).getByPath("data", JSONArray.class);
        List<XxlJobInfo> list = array.stream()
                .map(o -> JSONUtil.toBean((JSONObject) o, XxlJobInfo.class))
                .collect(Collectors.toList());

        return list;
    }

    /**
     * 添加任务
     * @param xxlJobInfo
     * @return
     */
    @Override
    public Integer addJobInfo(XxlJobInfo xxlJobInfo) {
        String url=adminAddresses+"/jobinfo/add";
        Map<String, Object> paramMap = BeanUtil.beanToMap(xxlJobInfo);
        HttpResponse response = HttpRequest.post(url)
                .form(paramMap)
                .cookie(jobLoginService.getCookie())
                .execute();

        JSON json = JSONUtil.parse(response.body());
        Object code = json.getByPath("code");
        if (code.equals(200)){
            return Convert.toInt(json.getByPath("content"));
        }
        throw new RuntimeException("add jobInfo error!");
    }

}
