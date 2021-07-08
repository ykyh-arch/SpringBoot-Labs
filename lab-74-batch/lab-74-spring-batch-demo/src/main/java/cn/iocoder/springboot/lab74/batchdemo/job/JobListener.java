package cn.iocoder.springboot.lab74.batchdemo.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * batch 任务监听器
 * 如果不需要在任务成功或者失败后做一些操作的话可以不加监听器，
 * 因为Batch自身包含日志执行情况日志（info级别），包括执行结果、执行参数、执行耗费时间等
 */
@Component
@Slf4j
public class JobListener extends JobExecutionListenerSupport {

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("任务[{}]执行成功，参数，执行时间：[{}]", jobExecution.getJobInstance().getJobName(),
					jobExecution.getJobParameters().getString("executedTime"));
		} else {
			log.info("任务[{}]执行失败", jobExecution.getJobInstance().getJobName());
			// TODO something，主要为日志记录
		}
	}
}