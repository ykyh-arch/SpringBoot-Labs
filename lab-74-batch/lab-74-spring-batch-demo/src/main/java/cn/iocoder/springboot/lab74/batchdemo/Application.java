package cn.iocoder.springboot.lab74.batchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * 参考：https://www.jianshu.com/p/0cd761c2e05e，https://www.jianshu.com/p/0b09802f4910，https://mp.weixin.qq.com/s/IwkgluQ4jiGBGlvKRomwGg
 *
 * 核心概念：
 * Job：最顶层的一个抽象概念，实现类：simplejob、flowjob，本质上可以看成step的一个容器，一对多关系
 * JobInstance：Job的更加底层的一个抽象，job运行当中，作业执行过程当中的概念。JobInstance = Job + JobParameters
 * JobParameters：标识一个jobinstance，包含一组用于启动批处理作业的参数
 * JobExecution：单次尝试运行一个我们定义好的Job的代码层面的概念。持久到数据库，对应表batch_job_execution
 * Step：封装了批处理作业的一个独立的阶段
 * StepExecution：表示一次执行Step，持久到数据库，对应表batch_step_execution
 * ExecutionContext：每一个StepExecution、JobExecution 的执行环境。
 * JobRepository：将上述job，step等概念进行持久化的一个类，提供CRUD操作。
 * JobLauncher：启动指定了JobParameters的Job
 * ItemReader：是一个读数据的抽象，实现类，比如JdbcPagingItemReader，JdbcCursorItemReader等等。
 * ItemWriter：是读数据的一个抽象，可以一次写一条数据，也可以一次写一个chunk的数据
 * ItemProcessor：对项目的业务逻辑处理的一个抽象
 * chunk：设定一个chunk size，spring batch 将一条一条处理数据，但不提交到数据库，只有当处理的数据数量达到chunk size设定的值得时候，才一起去commit
 * skipLimit()：允许的这个step可以跳过的异常数量
 * skip()：可以跳过的异常
 * noSkip()：不想跳过异常
 * JobLocator：可以根据jobName获取到指定的job，可以配合JobRepository、JobLauncher来手动启动job
 *
 * @author Jaquez
 * @date 2021/07/07 20:45
 */
@SpringBootApplication
public class Application {
    /*
     *一个典型的批处理应用程序大致如下：从数据库、文件或队列中读取大量记录，以某种方式处理数据，以修改之后的形式写回数据。
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 模拟 Java 程序关闭是一个过程。
     * @author Jaquez
     * @date 2021/05/28 16:14
     * @return Listener
     */
    @Component
    public class Listener implements ApplicationListener<ApplicationEvent> {

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            if (event instanceof ContextClosedEvent) {
                this.sleep(1);
            }
        }

        private void sleep(int seconds) {
            try {
                Thread.sleep(seconds * 1000L);
            } catch (InterruptedException ignore) {
            }
        }

    }

}
