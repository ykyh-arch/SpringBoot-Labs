package cn.iocoder.springboot.lab28.task.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Service;

/**
 * MyShardingJob
 *
 * @author jaquez
 * @date 2022/06/03 22:59
 **/
@Slf4j
@Service
public class MyShardingJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {

        switch (shardingContext.getShardingItem()) {
            case 0:
                log.info("分片1：执行任务");
                break;
            case 1:
                log.info("分片2：执行任务");
                break;
            case 2:
                log.info("分片3：执行任务");
                break;
        }
    }
}
