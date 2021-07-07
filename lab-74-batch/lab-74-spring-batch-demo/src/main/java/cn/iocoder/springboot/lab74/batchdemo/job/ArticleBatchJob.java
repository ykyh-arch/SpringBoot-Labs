package cn.iocoder.springboot.lab74.batchdemo.job;

import cn.iocoder.springboot.lab74.batchdemo.Reader.ArticleReaderDemo;
import cn.iocoder.springboot.lab74.batchdemo.entity.Article;
import cn.iocoder.springboot.lab74.batchdemo.entity.ArticleDetail;
import cn.iocoder.springboot.lab74.batchdemo.processor.ArticleProcessor;
import cn.iocoder.springboot.lab74.batchdemo.writer.ArticleJdbcWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 任务配置
 *
 */
@Configuration
@EnableBatchProcessing
public class ArticleBatchJob {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	private ArticleReaderDemo articleReader;
	@Autowired
	private ArticleProcessor articleProcessor;
	@Autowired
	private ArticleJdbcWriter articleJdbcWriter;

	@Bean(name = "articleReader")
	@StepScope
	public JdbcPagingItemReader<Article> batchReader(@Value("#{jobParameters['executedTime']}") String executedTime) {
		return articleReader.getArticlePaging(executedTime);
	}

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
	public Step step(JdbcPagingItemReader<Article> articleReader, ItemWriter<ArticleDetail> articleWriter) {
		return stepBuilderFactory.get("crossHistoryStep")
				// 数据会累积到一定量再提交到writer
				.<Article, ArticleDetail>chunk(10)
				.reader(articleReader)
				.processor(articleProcessor)
				.writer(articleWriter)
				// 默认为false（如果参数未发生变化的话，任务不会重复执行），Job如果执行成功一次，下次任务启动时如果参数没有变化的话，默认情况下是不会重复执行的，如果想要执行可以传一个时间参数或者设置allowStartIfComplete(true)
				.allowStartIfComplete(true)
				.build();
	}
}
