package cn.iocoder.springboot.lab28.task.job;

import cn.iocoder.springboot.lab28.job.annotation.XxlRegister;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * Xxl-Job 魔改版
 */
@Component
public class DemoXxlPlusJob {

    @XxlJob(value = "testJobHandler")
    @XxlRegister(cron = "0 * * * * ? *",
            author = "jaquez",
            jobDesc = "测试job处理器")
    public void testJob(){
        XxlJobHelper.log("XXL-JOB-PLUS, test JobHandler.");
        System.out.println("测试job处理器");
    }

}
