package cn.iocoder.springboot.lab37.loggingtlog.config;

import com.yomahub.tlog.core.thread.TLogInheritableTask;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * 线程池配置
 *
 **/
@Configuration
public class ThreadPoolConfig
{
    // 核心线程
    private final static int CORE_POOL_SIZE = 50;
    // 核心线程
    private final static int SCHEDULER_CORE_POOL_SIZE = 25;
    // 最大线程
    private final static int MAX_POOL_SIZE = 100;
    // 空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 初始化延迟
    private final static int INITDELAY = 0;
    // 缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 1000;
    // 缓冲队列大小
    private final static int PERIOD = 1000;
    // 缓冲队列
    private Queue<Runnable> queueCache = new LinkedBlockingQueue<Runnable>();

    @Bean
    public ThreadPoolExecutor threadPoolExecutor()
    {
        return new TlogThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new ArrayBlockingQueue(WORK_QUEUE_SIZE),
                (r,executor) ->{
                    // 多余任务添加到缓冲队列
                    queueCache.offer(r);
        });
    }

    /**
     * 自定义调度拒绝策略逻辑处理
     */
    @PostConstruct
    private void scheduledFuture()
    {
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(
                SCHEDULER_CORE_POOL_SIZE,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build()
        );
        scheduler.scheduleAtFixedRate(() ->{
            if(!queueCache.isEmpty()){
                // 当线程池的队列容量少于 WORK_QUEUE_SIZE，则开始把缓冲队列的任务加入到线程池
                if (threadPoolExecutor().getQueue().size() < WORK_QUEUE_SIZE) {
                    Runnable r = queueCache.poll();
                    threadPoolExecutor().execute(r);
                }
            }
        },INITDELAY, PERIOD, TimeUnit.MILLISECONDS);

    }

    static final class TlogThreadPoolExecutor extends ThreadPoolExecutor {

        public TlogThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public TlogThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public TlogThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public TlogThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        public void execute(Runnable command) {
            super.execute(wrapIfnecessary(command));
        }

        private TLogInheritableTask wrapIfnecessary(Runnable command) {
            if (command instanceof TLogInheritableTask) {
                return (TLogInheritableTask)command;
            }
            return new TLogInheritableTask() {
                @Override
                public void runTask() {
                    command.run();
                }
            };
        }
    }
}
