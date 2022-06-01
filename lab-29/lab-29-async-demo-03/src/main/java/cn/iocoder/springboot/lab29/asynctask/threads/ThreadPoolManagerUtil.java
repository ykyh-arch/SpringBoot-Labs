package cn.iocoder.springboot.lab29.asynctask.threads;

import cn.iocoder.springboot.lab29.asynctask.utils.SpringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * 线程池管理类，开始的时候直接执行插入到系统，当线程池的容量已经满了，则使用 RejectedExecutionHandler 方法把后面的订单添加到 Queue 缓冲队列，使用 ScheduledFuture 方法定时(我这里是每秒一次)检查 Queue 队列，重新把队列里面的订单添加到线程池，执行后面的插入任务。
 *
 * @author jaquez
 * @date 2022/05/31 16:15
 **/
public class ThreadPoolManagerUtil<T extends CommonThread> {

    /**
     * 单例模式
     */
    private ThreadPoolManagerUtil(){}

    private static ThreadPoolManagerUtil me = new ThreadPoolManagerUtil<>();

    public static <T extends CommonThread> ThreadPoolManagerUtil me()
    {
        return me;
    }

    private RedisTemplate redisTemplate = SpringUtils.getBean(StringRedisTemplate.class);

    // 缓存时间，一天
    private final static long KEEP_CACHE_TIME = 24*60*60;

    // 核心线程
    private final static int CORE_POOL_SIZE = 5;

    // 核心线程
    private final static int SCHEDULER_CORE_POOL_SIZE = 5;
    // 最大线程
    private final static int MAX_POOL_SIZE = 10;
    // 空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 100;

    /**
     * 用于储存在队列中的订单，防止重复提交，在真实场景中，可用 redis 代替（过期策略），验证重复
     */
    Map<String, Object> cacheMap = new ConcurrentHashMap<>();
    /**
     * 订单的缓冲队列，当线程池满了，则将订单存入到此缓冲队列
     */
    Queue<T> msgQueue = new LinkedBlockingQueue<T>();

    /**
     * 当线程池的容量满了，执行下面代码，将执行拒绝策略（将多余的任务缓存起来）
     */
    final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //订单加入到缓冲队列
            msgQueue.offer((T)r);
            System.out.println("系统任务太忙了，把此任务交给(调度线程池)逐一处理，任务名称为：" + ((T)r).getTaskName());
        }
    };

    /**
     * 创建线程池
     */
    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE), this.handler);

    /**
     * 将任务加入订单线程池
     * @param task
     */
    public void addTask(T task){
        System.out.println("此任务准备添加到线程池，任务名称为：" + task.getTaskName());
        // 验证当前进入的任务是否已经存在，cacheMap，防止重复提交
        if (!StringUtils.isEmpty(task.getTaskParam()) && redisTemplate.opsForValue().get(task.getTaskParam()) == null) {
            redisTemplate.opsForValue().set(task.getTaskParam(), task.getTaskParam(),KEEP_CACHE_TIME,TimeUnit.SECONDS);
            threadPool.execute(task);
        }
        if(StringUtils.isEmpty(task.getTaskParam())){
            threadPool.execute(task);
        }
    }

    /**
     * 线程池的定时任务----> 称为(调度线程池)。此线程池支持定时以及周期性执行任务的需求。
     */
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(SCHEDULER_CORE_POOL_SIZE);


    /**
     * 检查(调度线程池)，每 100 毫秒执行一次，查看订单的缓冲队列是否有订单记录，则重新加入到线程池
     */
    final ScheduledFuture scheduledFuture = scheduler.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            // 判断缓冲队列是否存在记录
            if(!msgQueue.isEmpty()){
                // 当线程池的队列容量少于 WORK_QUEUE_SIZE，则开始把缓冲队列的订单加入到线程池
                if (threadPool.getQueue().size() < WORK_QUEUE_SIZE) {
                    T t = msgQueue.poll();
                    threadPool.execute(t);
                    System.out.println("(调度线程池)缓冲队列出现业务任务，重新添加到线程池，任务名称为："+t.getTaskName());
                }
            }
        }
    }, 0, 100, TimeUnit.MILLISECONDS);


    /**
     * 获取消息缓冲队列
     * @return
     */
    public Queue<T> getMsgQueue() {
        return msgQueue;
    }

    /**
     * 终止任务线程池 + 调度线程池
     */
    public void shutdown() {
        // true 表示如果定时任务在执行，立即中止，false 则等待任务结束后再停止
        System.out.println("终止任务线程池+调度线程池："+scheduledFuture.cancel(false));
        scheduler.shutdown();
        threadPool.shutdown();
    }

}
