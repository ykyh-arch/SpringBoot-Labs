package cn.iocoder.springboot.lab28.task.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
//import com.xxl.job.core.handler.annotation.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

// @Component // 临时关闭
// @JobHandler("demoJob")    // 对应版本：2.1.1，踩坑：在新版本2.3.0已经不识别
public class DemoJob extends IJobHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicInteger counts = new AtomicInteger();

    // @Override
    public ReturnT<String> execute(String param) throws Exception {
        // 打印日志
        logger.info("[execute][定时第 ({}) 次执行]", counts.incrementAndGet());
        // 返回执行成功
        return ReturnT.SUCCESS;
    }

    @Override
    public void execute() throws Exception {
        // 打印日志
        logger.info("[execute][定时第 ({}) 次执行]", counts.incrementAndGet());
    }

}
