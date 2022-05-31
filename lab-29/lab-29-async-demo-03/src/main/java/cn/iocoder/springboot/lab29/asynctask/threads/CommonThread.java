package cn.iocoder.springboot.lab29.asynctask.threads;

import org.springframework.stereotype.Component;

/**
 * 公用线程类
 *
 * @author jaquez
 * @date 2022/05/31 16:10
 **/
@Component
public abstract class CommonThread implements Runnable{

    private String taskName = "default-thread-task";

    public String getTaskName() {
        return Thread.currentThread().getName() + "-" + taskName + "-" +System.currentTimeMillis();
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {

    }
}
