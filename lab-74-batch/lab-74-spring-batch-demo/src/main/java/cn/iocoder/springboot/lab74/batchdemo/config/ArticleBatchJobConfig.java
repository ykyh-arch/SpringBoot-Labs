package cn.iocoder.springboot.lab74.batchdemo.config;

import cn.iocoder.springboot.lab74.batchdemo.batchs.listener.JobListener;
import cn.iocoder.springboot.lab74.batchdemo.batchs.processor.ArticleProcessor;
import cn.iocoder.springboot.lab74.batchdemo.batchs.reader.ArticleJdbcReader;
import cn.iocoder.springboot.lab74.batchdemo.batchs.writer.ArticleJdbcWriter;
import cn.iocoder.springboot.lab74.batchdemo.entity.Article;
import cn.iocoder.springboot.lab74.batchdemo.entity.ArticleDetail;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 批处理 Job 任务配置，整个流程是：jobLauncher 启动 -》Job -> Step -> 。。。
 *
 * @author Jaquez
 * @date 2022/02/24 16:21
 */
@Configuration
@EnableBatchProcessing // 开启批处理，默认自动执行Job，需要在配置文件设置 spring.batch.job.enabled=false
public class ArticleBatchJobConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	private ArticleJdbcReader articleReader;
	@Autowired
	private ArticleProcessor articleProcessor;
	@Autowired
	private ArticleJdbcWriter articleJdbcWriter;

	@Bean(name = "articleReader")
	@StepScope // 生成代理类
	public JdbcPagingItemReader<Article> batchReader(@Value("#{jobParameters['executedTime']}") String executedTime) {
		return articleReader.getArticlePaging(executedTime);
	}

//	@Bean(name = "articleReader")
//	@StepScope
//	public FlatFileItemReader<Article> batchReader() {
//		return articleReader.readCsv();
//	}

	@Bean(name = "articleWriter")
	public ItemWriter<ArticleDetail> batchWriter() {
		return articleJdbcWriter.writer();
//		return new ArticleWriter();
	}

	@Bean(name = "articleJob")
	public Job batchJob(JobListener listener, Step articleStep) {
		return jobBuilderFactory.get("articleJob")
				.listener(listener)
				.incrementer(new RunIdIncrementer())
				.flow(articleStep)
				.end()
				.build();
	}

	@Bean(name = "articleStep")
	public Step step(@Autowired @Qualifier("articleReader")
							 ItemReader<Article> articleReader, ItemWriter<ArticleDetail> articleWriter) {
		return stepBuilderFactory.get("crossHistoryStep")
				// 数据会累积到一定量再提交到 writer
				.<Article, ArticleDetail>chunk(10)
				.reader(articleReader)
				.processor(articleProcessor)
				.writer(articleWriter)
				// 默认为 false（如果jobParameters参数未发生变化的话，任务不会重复执行），Job如果执行成功一次，下次任务启动时如果参数没有变化的话，默认情况下是不会重复执行的，如果想要执行可以传一个时间参数或者设置allowStartIfComplete(true)
				.allowStartIfComplete(false)
				.build();
	}

}
