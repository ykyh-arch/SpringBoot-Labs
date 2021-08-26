package cn.iocoder.springboot.lab28.task.config;

import org.springframework.lang.Nullable;
import org.springframework.scheduling.config.Task;

import java.util.concurrent.ScheduledFuture;

/**
 * 重写默认的类
 *
 * @author jaquez
 * @date 2021/08/26 15:42
 **/
public class ScheduledTask {

    @Nullable
    volatile ScheduledFuture<?> future;

    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }

    }

}
