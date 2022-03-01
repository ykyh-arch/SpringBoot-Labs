package cn.iocoder.springboot.lab74.batchdemo.config;

import cn.iocoder.springboot.lab74.batchdemo.batchs.listener.JobListener;
import cn.iocoder.springboot.lab74.batchdemo.batchs.processor.ArticleProcessor;
import cn.iocoder.springboot.lab74.batchdemo.batchs.reader.ArticleJdbcReader;
import cn.iocoder.springboot.lab74.batchdemo.batchs.writer.ArticleJdbcWriter;
import cn.iocoder.springboot.lab74.batchdemo.entity.Article;
import cn.iocoder.springboot.lab74.batchdemo.entity.ArticleDetail;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.concurrent.ThreadPoolExecutor;

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


	@Bean // 参考：https://www.codingdict.com/questions/6372
	public BatchConfigurer configurer(@Qualifier("btDataSource") DataSource dataSource){
		return new DefaultBatchConfigurer(dataSource);
	}

	/**
	 * 注册 jobRegistry，如果没有该项配置，则手动启动时会报错 No job configuration with the name [XJob] was registered
	 */
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry){
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;
	}

	@Bean	// 参考自：https://blog.csdn.net/warmer_winter/article/details/89640104
	protected ThreadPoolTaskExecutor taskExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(50);
		executor.setKeepAliveSeconds(1000);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
		executor.setQueueCapacity(10000);
		executor.setThreadNamePrefix("spring_batch_demo_");
		executor.setThreadGroupName("spring_batch");
		return executor;
	}

	@Bean(name = "articleReader")
	@StepScope // 生成代理类，原因是参数 #{jobParameters['executedTime']} 属于运行时才产生的参数变量
	public MyBatisPagingItemReader<Article> batchReader(@Value("#{jobParameters['executedTime']}")
																 String executedTime) throws Exception {
		return articleReader.getArticleByMyBatisPage();
	}

	@Bean(name = "articleWriter")
	public ItemWriter<ArticleDetail> batchWriter() throws Exception {
		return articleJdbcWriter.writerByMybatis();
	}

	@Bean(name = "articleJob")
	public Job batchJob(JobListener listener, Step articleStep) {
		return jobBuilderFactory.get("articleJob")
				.listener(listener)
				.incrementer(new RunIdIncrementer())
				.flow(articleStep)
				// .start(articleStep) // 这里可以执行多个 step 步骤
				.end()
				.build();
	}

	@Bean(name = "articleStep")
	public Step step(@Qualifier("articleReader")
							 ItemReader<Article> articleReader, ItemWriter<ArticleDetail> articleWriter) {
		return stepBuilderFactory.get("crossHistoryStep")
				// 数据会累积到一定量再提交到 writer
				.<Article, ArticleDetail>chunk(50)
				.reader(articleReader)
				.processor(articleProcessor)
				.writer(articleWriter)
				// 默认为 false（如果jobParameters参数未发生变化的话，任务不会重复执行），Job如果执行成功一次，下次任务启动时如果参数没有变化的话，默认情况下是不会重复执行的，如果想要执行可以传一个时间参数或者设置allowStartIfComplete(true)
				.allowStartIfComplete(false)
				// .taskExecutor(taskExecutor())	// 自定义线程池配置，踩坑：这里不作配置，因为后面使用 scheduler 调度线程池去执行任务，若这里配置了就需要将自定义的线程池执行信息传递给调度线程池（线程池通讯），否则报错
				.build();
	}

}
