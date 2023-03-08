package cn.iocoder.springboot.lab28.powerjob.processors;

import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;
import tech.powerjob.worker.log.OmsLogger;

import java.util.Optional;

/**
 * 单机 - 简单任务
 */
public class SimpleProcessor implements BasicProcessor {


    @Override
    public ProcessResult process(TaskContext context) throws Exception {

        OmsLogger logger = context.getOmsLogger();

        String jobParams = Optional.ofNullable(context.getJobParams()).orElse("S");
        logger.info("Current context:{}", context.getWorkflowContext());
        logger.info("Current job params:{}", jobParams);

        return jobParams.contains("F") ? new ProcessResult(false) : new ProcessResult(true, "yeah!");

    }
}