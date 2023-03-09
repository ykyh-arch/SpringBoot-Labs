package cn.iocoder.springboot.lab28.powerjob.processors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import tech.powerjob.common.serialize.JsonUtils;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.TaskResult;
import tech.powerjob.worker.core.processor.sdk.MapReduceProcessor;
import tech.powerjob.worker.log.OmsLogger;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * MapReduce 处理器 示例
 * 控制台参数：{"batchSize": 100, "batchNum": 2}
 *
 */
@Slf4j
@Component
public class MapReduceProcessorDemo implements MapReduceProcessor {

    @Override
    public ProcessResult process(TaskContext context) throws Exception {

        OmsLogger omsLogger = context.getOmsLogger();

        log.info("============== TestMapReduceProcessor#process ==============");
        log.info("isRootTask:{}", isRootTask());
        log.info("taskContext:{}", JsonUtils.toJSONString(context));

        // 根据控制台参数获取 MR 批次及子任务大小
        final JSONObject jobParams = JSONObject.parseObject(context.getJobParams());

        Integer batchSize = (Integer) jobParams.getOrDefault("batchSize", 100);
        Integer batchNum = (Integer) jobParams.getOrDefault("batchNum", 10);

        if (isRootTask()) {
            log.info("==== MAP ====");
            omsLogger.info("[DemoMRProcessor] start root task~");
            List<TestSubTask> subTasks = Lists.newLinkedList();
            for (int j = 0; j < batchNum; j++) { // 批次
                for (int i = 0; i < batchSize; i++) { // 大小
                    int x = j * batchSize + i;
                    subTasks.add(new TestSubTask("name_" + x, x));
                }
                // 处理子任务
                map(subTasks, "MAP_TEST_TASK");
                subTasks.clear();
            }
            omsLogger.info("[DemoMRProcessor] map success~");
            return new ProcessResult(true, "MAP_SUCCESS");
        } else {
            log.info("==== NORMAL_PROCESS ====");
            omsLogger.info("[DemoMRProcessor] process subTask: {}.", JSON.toJSONString(context.getSubTask()));
            log.info("subTask: {}", JsonUtils.toJSONString(context.getSubTask()));
            // Thread.sleep(1000);
            if (context.getCurrentRetryTimes() == 0) {
                return new ProcessResult(false, "FIRST_FAILED");
            } else {
                return new ProcessResult(true, "PROCESS_SUCCESS");
            }
        }
    }

    @Override
    public ProcessResult reduce(TaskContext context, List<TaskResult> taskResults) {
        log.info("================ MapReduceProcessorDemo#reduce ================");
        log.info("TaskContext: {}", JSONObject.toJSONString(context));
        log.info("List<TaskResult>: {}", JSONObject.toJSONString(taskResults));
        context.getOmsLogger().info("MapReduce job finished, result is {}.", taskResults);

        boolean success = ThreadLocalRandom.current().nextBoolean();
        return new ProcessResult(success, context + ": " + success);
    }

    // 自定义子任务
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestSubTask {
        private String name;
        private int age;
    }
}
