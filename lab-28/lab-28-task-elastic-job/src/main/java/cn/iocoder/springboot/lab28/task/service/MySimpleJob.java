package cn.iocoder.springboot.lab28.task.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Service;

/**
 * MySimpleJob
 *
 * @author jaquez
 * @date 2022/06/02 15:48
 **/
@Slf4j
@Service
public class MySimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("MySimpleJob start : {}", System.currentTimeMillis());
    }
}
